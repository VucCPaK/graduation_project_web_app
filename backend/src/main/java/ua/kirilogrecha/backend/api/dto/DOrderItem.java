package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;
import ua.kirilogrecha.backend.api.entities.EPicture;

import java.math.BigDecimal;

@Getter
@Setter
public class DOrderItem {
    private String ePicture;
    private String name;
    private String type;
    private int quantity;
    private BigDecimal price;
    private String itemId;


    public DOrderItem(String ePicture, String name, String type, int quantity, BigDecimal price, String itemId) {
        this.ePicture = ePicture;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.itemId = itemId;
    }
}
