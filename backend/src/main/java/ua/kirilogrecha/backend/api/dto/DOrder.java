package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DOrder {
    private String id;
    private String date;
    private BigDecimal totalPrice;
    private boolean isSent;

    public DOrder() {
    }
}
