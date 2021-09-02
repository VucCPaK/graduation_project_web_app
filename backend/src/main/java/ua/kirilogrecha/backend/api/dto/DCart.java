package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DCart {
    private List<DCart> items;
    private BigDecimal totalPrice;
}
