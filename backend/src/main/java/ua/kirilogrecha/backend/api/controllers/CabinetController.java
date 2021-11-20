package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.services.CabinetService;

import java.util.List;

@RestController
@RequestMapping("/api/cabinet")
public class CabinetController {
    private final CabinetService cabinetService;

    @Autowired
    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'supplier', 'storekeeper', 'customer')")
    public List<DOrder> getList(@PathVariable("id") String id) {
        return cabinetService.getListByUserId(id);
    }
}
