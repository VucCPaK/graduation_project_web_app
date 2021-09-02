package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DCartOrder {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String ePicture;

    public DCartOrder() {
        this.quantity = 1;
    }
}