package ua.kirilogrecha.backend.api.services;

import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DItem;

import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.entities.EPicture;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterService {
    public ConverterService() {
    }

    public DItem fromEItemToDItem(EItem eItem) {
        var dItem = new DItem();

        dItem.setId(eItem.getId());
        dItem.setName(eItem.getName());
        dItem.setPrice(eItem.getPrice());
        dItem.setAmount(eItem.getAmount());
        dItem.setDescription(eItem.getDescription());
        dItem.setEPicture(eItem.getEPicture().getName());
        dItem.setCategories(eItem.getCategories());
        dItem.setType(eItem.getType());
        dItem.setPictures(eItem.getPictures().stream()
                .map(EPicture::getName)
                .collect(Collectors.toList()));

        return dItem;
    }

    public List<DItem> fromEItemToDItem(List<EItem> eItems) {
        return eItems.stream()
                .map(this::fromEItemToDItem)
                .collect(Collectors.toList());
    }

//    public EOrder fromDCartItemToDOrder() {
//        var eOrder = new EOrder();
//
//
//    }
}
