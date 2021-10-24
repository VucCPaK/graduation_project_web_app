package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DWarehouseItem;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;

import java.util.List;

@Service
public class WarehouseService {
    private final OrderRepository orderRepository;

    @Autowired
    public WarehouseService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<EOrder> getList() {
        return orderRepository.getList();
    }

    public void updateIsSentField(List<DWarehouseItem> items) {
        items.forEach(item -> orderRepository.updateIsSentField(item.getId()));
    }
}
