package ua.kirilogrecha.backend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Statistic {
    private String name;
    private long amountStored;
    private long quantitySold;
    private BigDecimal proceeds;
    private BigDecimal profit;

}
