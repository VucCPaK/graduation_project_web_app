package ua.kirilogrecha.backend.api.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ua.kirilogrecha.backend.api.dto.DCartItem;
import ua.kirilogrecha.backend.api.entities.EOrderItem;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.OrderItemRepository;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
@SessionScope
public class CartService {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public CartService(ItemRepository itemRepository,
                       OrderRepository orderRepository,
                       OrderItemRepository orderItemRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public BigDecimal getTotalPrice(List<DCartItem> items) {

//        List<DCartItem> items = dCart.getItems();
        return items.isEmpty() ? BigDecimal.valueOf(0) :
                // get price from repository and multiply by quantity
                items.stream()
                        .map(dco ->
                                itemRepository
                                        .findById(dco.getId())
                                        .get()
                                        .getPrice()
                                        .multiply(BigDecimal.valueOf(dco.getQuantity())))
                        // as .sum()
                        .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public void toOrder(List<DCartItem> items, String userId) {
        // check all restrictions
        // ...
        boolean examination = items.stream()
                .allMatch(dco -> itemRepository
                        .findById(dco.getId())
                        .get()
                        .getAmount() >= dco.getQuantity());

        if (examination) {
            createEOrderEntity(items, userId);
            items.forEach(item -> itemRepository.updateAmount(item.getId(), item.getQuantity()));
        } else {
            throw new RuntimeException("We don't have that many products");
        }
    }

    private void createEOrderEntity(List<DCartItem> items, String userId) {
        EOrder eOrder = new EOrder();

        // using Set that avoid duplicate records
        Set<EOrderItem> list = items.stream().
                map(item -> {
                    EOrderItem eOrderItem = new EOrderItem();
                    eOrderItem.setEOrder(eOrder);
                    eOrderItem.setEItem(itemRepository.findById(item.getId()).get());
                    eOrderItem.setQuantity(item.getQuantity());

                    return eOrderItem;
                })
        .collect(Collectors.toCollection(HashSet::new));

        eOrder.setTotalPrice(getTotalPrice(items));
        eOrder.setItems(list);
        eOrder.setUserId(userId);

        orderRepository.save(eOrder);
        list.forEach(eItemsInOrder -> orderItemRepository.save(eItemsInOrder));
    }

}