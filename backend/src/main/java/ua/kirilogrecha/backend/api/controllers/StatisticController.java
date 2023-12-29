package ua.kirilogrecha.backend.api.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.Period;
import ua.kirilogrecha.backend.api.services.StatisticService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/backend/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PreAuthorize("hasAuthority('supplier')")
    @GetMapping("/supplier/{id}")
    public ResponseEntity<Resource> getStatisticsByPeriod(
            @PathVariable String id,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws Exception {

        java.sql.Date startDateSql;
        java.sql.Date endDateSql;
        try {
            startDateSql = new java.sql.Date(dateFormat.parse(startDate).getTime());
            endDateSql = new java.sql.Date(dateFormat.parse(endDate).getTime());
        } catch (ParseException e) {
            throw new Exception("Parse error");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statistic.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(statisticService.getDataByPeriod(new Period(startDateSql, endDateSql), id));
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin")
    public ResponseEntity<Resource> getStatisticsByPeriod(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws Exception {

        java.sql.Date startDateSql;
        java.sql.Date endDateSql;
        try {
            startDateSql = new java.sql.Date(dateFormat.parse(startDate).getTime());
            endDateSql = new java.sql.Date(dateFormat.parse(endDate).getTime());
        } catch (ParseException e) {
            throw new Exception("Parse error");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statistic.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(statisticService.getDataByPeriod(new Period(startDateSql, endDateSql)));
    }

}
