package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DItem;
import ua.kirilogrecha.backend.api.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping()
    public List<DItem> getList() {
        return itemService.getList();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public DItem getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

}
