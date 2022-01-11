package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DOrderItem;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.services.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/backend/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'storekeeper')")
    @GetMapping()
    public List<DOrder> getList() {
        return warehouseService.getList();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'storekeeper')")
    @PostMapping("/{id}")
    public void updateIsSent(@PathVariable String id, @RequestParam("sent") Boolean sent) {
        warehouseService.updateIsSentField(id, sent);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'storekeeper')")
    @GetMapping("/{id}")
    public List<DOrderItem> getItemsInOrder(@PathVariable("id") String id) {
        return warehouseService.getItemsInOrder(id);
    }
}
