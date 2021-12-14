package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DOrderItem {
    private String name;
    private String type;
    private int quantity;
    private BigDecimal price;
    private String itemId;
    private List<String> pictures;

    public DOrderItem(String name, String type, int quantity, BigDecimal price, String itemId, List<String> pictures) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.itemId = itemId;
        this.pictures = pictures;
    }
}