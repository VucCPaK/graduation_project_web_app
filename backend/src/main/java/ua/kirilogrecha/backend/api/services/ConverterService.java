package ua.kirilogrecha.backend.api.services;

import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DCartOrder;
import ua.kirilogrecha.backend.api.dto.DItem;
import ua.kirilogrecha.backend.api.dto.DPerson;
import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EPerson;
import ua.kirilogrecha.backend.api.entities.EPicture;

import javax.naming.Name;
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

//    public EPerson fromDPersonToEPerson(DPerson dPerson) {
//        var ePerson = new EPerson();
//
//        // convert from string to dn (distinguish name (active directory))
//        Name id = LdapNameBuilder.newInstance()
//                .add("ou", "users")
//                .build();
//
//        String encodedPassword = new BCryptPasswordEncoder().encode(dPerson.getPassword());
//
//        ePerson.setId(id);
//        ePerson.setPassword(encodedPassword);
//        ePerson.setFirstName(dPerson.getFirstName());
//        ePerson.setLastName(dPerson.getLastName());
//        ePerson.setMail(dPerson.getMail());
//        ePerson.setPhone(dPerson.getPhone());
//
//        return ePerson;
//    }
//
//    public List<EPerson> fromDPersonToEPerson(List<DPerson> dPersons) {
//        return dPersons.stream()
//                .map(this::fromDPersonToEPerson)
//                .collect(Collectors.toList());
//    }
}
