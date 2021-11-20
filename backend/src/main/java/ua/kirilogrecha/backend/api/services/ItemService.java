package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DItem;
import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.exceptions.ItemNotFoundException;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.PictureRepository;
import ua.kirilogrecha.backend.api.services.converters.ItemConverterService;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemConverterService itemConverterService;
    private final PictureRepository pictureRepository;

    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            ItemConverterService itemConverterService, PictureRepository pictureRepository) {
        this.itemRepository = itemRepository;
        this.itemConverterService = itemConverterService;
        this.pictureRepository = pictureRepository;
    }

    public List<DItem> getList() {
        return itemConverterService
                .fromEItemToDItem(itemRepository.findAll());
    }

    public DItem getItemById(String id) {
        return itemConverterService
                .fromEItemToDItem(
                        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id))
                );
    }

    public void updateItem(String id, DItem dItem) {
        EItem eItem = itemRepository.getOne(id);

        eItem.setName(dItem.getName());
        eItem.setPrice(dItem.getPrice());
        eItem.setAmount(dItem.getAmount());
        eItem.setDescription(dItem.getDescription());
        dItem.setPictures(dItem.getPictures());
        eItem.setCategories(dItem.getCategories());
        eItem.setType(dItem.getType());

        itemRepository.save(eItem);
    }

    public String newItem(DItem dItem) {
        EItem eItem = new EItem();

        eItem.setName(dItem.getName());
        eItem.setPrice(dItem.getPrice());
        eItem.setAmount(dItem.getAmount());
        eItem.setDescription(dItem.getDescription());
        eItem.setCategories(dItem.getCategories());
        eItem.setType(dItem.getType());
        eItem.setSupplierId(dItem.getSupplierId());

        return itemRepository.save(eItem).getId();
    }

}