package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DPicture {
    private String name;
    private Long itemId;
    private Long mainId;

    public DPicture() {
    }
}
