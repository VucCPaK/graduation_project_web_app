package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DCartItem {
    private String id;
    private Integer quantity;

    public DCartItem() {
        this.quantity = 1;
    }
}