package ua.kirilogrecha.backend.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DCartOrder;
import ua.kirilogrecha.backend.api.services.CartService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{id}")
    public void add(@RequestBody DCartOrder dCartOrder) throws Exception {
        cartService.add(dCartOrder);
    }

    @DeleteMapping("/{id}")
    public List<DCartOrder> remove(@PathVariable("id") String id) throws Exception {
        return cartService.remove(id);
    }

    @GetMapping()
    public List<DCartOrder> getList() {
        return cartService.getList();
    }

    @GetMapping("/{id}")
    public boolean isPresentInCart(@PathVariable("id") String id) {
        return cartService.isPresentInCart(id);
    }

    @PostMapping("/amount")
    public void updateAmount(@RequestBody DCartOrder dCartOrder) throws Exception {
        cartService.updateAmount(dCartOrder);
    }

    @GetMapping(value = "/totalPrice")
    public BigDecimal getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @GetMapping("/order")
    public synchronized void toOrder() throws Exception {
        cartService.toOrder();
    }
}