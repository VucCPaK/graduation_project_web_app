package ua.kirilogrecha.backend.api.services;

import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DItem;
import ua.kirilogrecha.backend.api.exceptions.ItemNotFoundException;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;
    private final ConverterService converterService;

    @Autowired
    public ItemService(
            ItemRepository repository,
            ConverterService converterService) {
        this.repository = repository;
        this.converterService = converterService;
    }

    public List<DItem> getList() {
        return converterService
                .fromEItemToDItem(repository.findAll());
    }

    public DItem getItemById(String id) {
        return converterService
                .fromEItemToDItem(
                        repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id))
                );
    }
}