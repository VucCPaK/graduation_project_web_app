package ua.kirilogrecha.backend.api.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ua.kirilogrecha.backend.api.dto.DCartItem;
import ua.kirilogrecha.backend.api.entities.EItemsInOrder;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.ItemsInOrderRepository;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Service
@SessionScope
public class CartService {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;
    private ItemsInOrderRepository itemsInOrderRepository;

    @Autowired
    public CartService(ItemRepository itemRepository,
                       OrderRepository orderRepository,
                       ItemsInOrderRepository itemsInOrderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.itemsInOrderRepository = itemsInOrderRepository;
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

    public void toOrder(List<DCartItem> items) {
        // check all restrictions
        // ...
        boolean examination = items.stream()
                .allMatch(dco -> itemRepository
                        .findById(dco.getId())
                        .get()
                        .getAmount() >= dco.getQuantity());

        if (examination) {
            createEOrderEntity(items);
            items.forEach(item -> itemRepository.updateAmount(item.getId(), item.getQuantity()));
        } else {
            throw new RuntimeException("We don't have that many products");
        }
    }

    private void createEOrderEntity(List<DCartItem> items) {
        EOrder eOrder = new EOrder();

        List<EItemsInOrder> list = items.stream().
                map(item -> {
                    EItemsInOrder eItemsInOrder = new EItemsInOrder();
                    eItemsInOrder.setEOrder(eOrder);
                    eItemsInOrder.setItemId(item.getId());
                    eItemsInOrder.setQuantity(item.getQuantity());
                    eItemsInOrder.setPrice(itemRepository.findById(item.getId()).get().getPrice()); // get price from 'item' table

                    return eItemsInOrder;
                })
        .collect(Collectors.toList());

        eOrder.setTotalPrice(getTotalPrice(items));
        eOrder.setItems(list);

        orderRepository.save(eOrder);
        list.forEach(eItemsInOrder -> itemsInOrderRepository.save(eItemsInOrder));
    }

//    // check
//    public void add(DCartOrder dco) throws Exception {
//        if (items.stream().anyMatch(itemOrder -> itemOrder.getId().equals(dco.getId())))
//            throw new Exception("This element is already in the list");
//
//        items.add(dco);
//    }
//
//    // check
//    public List<DCartOrder> remove(String id) throws Exception {
//        DCartOrder dCartOrder = items.stream()
//                .filter(dco -> dco.getId().equals(id))
//                .findAny()
//                .orElse(null);
//
//        if (dCartOrder == null)
//            throw new Exception("This element is not in the list");
//
//        items.remove(dCartOrder);
//        return items;
//    }
//
//    public List<DCartOrder> getList() {
//        return items;
//    }
//
//    // check
//    public boolean isPresentInCart(String id) {
//        return items.stream()
//                .anyMatch(dco -> dco.getId().equals(id));
//    }
//
//    // check
//    public void updateAmount(DCartOrder dCartOrder) throws Exception {
//        // check all restrictions
//        // ...
//        if (dCartOrder.getQuantity() <= 0)
//            throw new Exception("Please enter the correct quantity");
//
//        items.stream()
//                .filter(dco -> dco.getId().equals(dCartOrder.getId()))
//                .findAny()
//                .get()
//                .setQuantity(dCartOrder.getQuantity());
//    }
}