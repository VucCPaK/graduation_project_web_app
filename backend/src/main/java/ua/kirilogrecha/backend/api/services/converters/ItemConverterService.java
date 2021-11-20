package ua.kirilogrecha.backend.api.services.converters;

import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DItem;

import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EPicture;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemConverterService {
    public ItemConverterService() {
    }

    public DItem fromEItemToDItem(EItem eItem) {
        var dItem = new DItem();

        dItem.setId(eItem.getId());
        dItem.setName(eItem.getName());
        dItem.setPrice(eItem.getPrice());
        dItem.setAmount(eItem.getAmount());
        dItem.setDescription(eItem.getDescription());
//        dItem.setEPicture(eItem.getEPicture().getName());
        dItem.setCategories(eItem.getCategories());
        dItem.setType(eItem.getType());
        dItem.setSupplierId(eItem.getSupplierId());
        dItem.setPictures(eItem.getPictures().stream()
                .sorted(Comparator.comparingLong(EPicture::getPriority))
                .map(EPicture::getName)
                .collect(Collectors.toList()));

        return dItem;
    }

    public List<DItem> fromEItemToDItem(List<EItem> eItems) {
        return eItems.stream()
                .map(this::fromEItemToDItem)
                .collect(Collectors.toList());
    }
}
