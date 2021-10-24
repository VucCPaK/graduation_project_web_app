package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DWarehouseItem;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.services.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'storekeeper')")
    @GetMapping()
    public List<EOrder> getList() {
        return warehouseService.getList();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'storekeeper')")
    @PostMapping("/item")
    public void updateIsSent(@RequestBody List<DWarehouseItem> items) {
        warehouseService.updateIsSentField(items);
    }
}
