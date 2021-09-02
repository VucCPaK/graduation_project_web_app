package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DItem {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer amount;
    private String description;
    private List<String> pictures;
    private String ePicture;
    private String categories;
    private String type;

    public DItem() {
    }
}
