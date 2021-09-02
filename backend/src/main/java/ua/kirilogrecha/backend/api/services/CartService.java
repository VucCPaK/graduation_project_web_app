package ua.kirilogrecha.backend.api.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ua.kirilogrecha.backend.api.dto.DCartOrder;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Service
@SessionScope
public class CartService {
    private ConverterService converterService;
    private ItemRepository itemRepository;
    private ArrayList<DCartOrder> items;

    @Autowired
    public CartService(ConverterService converterService,
                       ItemRepository itemRepository) {
        this.converterService = converterService;
        this.itemRepository = itemRepository;
        this.items = new ArrayList<>();
    }

    public void add(DCartOrder dco) throws Exception {
        if (items.stream().anyMatch(itemOrder -> itemOrder.getId().equals(dco.getId())))
            throw new Exception("This element is already in the list");

        items.add(dco);
    }

    public List<DCartOrder> remove(String id) throws Exception {
        DCartOrder dCartOrder = items.stream()
                .filter(dco -> dco.getId().equals(id))
                .findAny()
                .orElse(null);

        if (dCartOrder == null)
            throw new Exception("This element is not in the list");

        items.remove(dCartOrder);
        return items;
    }

    public List<DCartOrder> getList() {
        return items;
    }

    public boolean isPresentInCart(String id) {
        return items.stream()
                .anyMatch(dco -> dco.getId().equals(id));
    }

//    public synchronized void updateAmount(DCartOrder dco) throws Exception {
//        // check all restrictions
//        // ...
//
//        itemRepository.updateAmount(dco.getId(), dco.getQuantity());
//    }

    public void updateAmount(DCartOrder dCartOrder) throws Exception {
        // check all restrictions
        // ...
        if (dCartOrder.getQuantity() <= 0)
            throw new Exception("Please enter the correct quantity");

        items.stream()
                .filter(dco -> dco.getId().equals(dCartOrder.getId()))
                .findAny()
                .get()
                .setQuantity(dCartOrder.getQuantity());
    }

    public BigDecimal getTotalPrice() {
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

    public void toOrder() throws Exception {
        // check all restrictions
        // ...
        boolean examination = items.stream()
                .allMatch(dco -> itemRepository
                        .findById(dco.getId())
                        .get()
                        .getAmount() >= dco.getQuantity());

        if (examination) {
            items.forEach(dco -> itemRepository
                    .updateAmount(dco.getId(), dco.getQuantity()));
            items.clear();
        } else {
            throw new Exception("We don't have that many products");
        }
    }
}