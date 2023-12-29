package ua.kirilogrecha.backend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Period {
    private Date startDate;
    private Date endDate;

    public Period() {
    }
}
