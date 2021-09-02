package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DItem;
import ua.kirilogrecha.backend.api.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<DItem> getList() {
        return itemService.getList();
    }

    @GetMapping("/{id}")
    public DItem getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

}
