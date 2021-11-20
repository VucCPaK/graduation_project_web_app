package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DOrderItem;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.repositories.OrderItemRepository;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;
import ua.kirilogrecha.backend.api.services.converters.OrderConverterService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    private final OrderRepository orderRepository;
    private final OrderConverterService orderConverterService;

    @Autowired
    public WarehouseService(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            OrderConverterService orderConverterService) {
        this.orderRepository = orderRepository;
        this.orderConverterService = orderConverterService;
    }

    public List<DOrder> getList() {
        return orderConverterService.fromEOrderToDOrder(orderRepository.getList());
    }

    public void updateIsSentField(String id, boolean sent) {
        orderRepository.updateIsSentField(id, sent);
    }

    public List<DOrderItem> getItemsInOrder(String id) {
        return orderRepository
                .findById(id)
                .flatMap(order -> Optional.ofNullable(order.getItems()))
                .stream()
                .flatMap(Collection::stream)
                .map(item -> new DOrderItem(
                        item.getEItem().getName(),
                        item.getEItem().getType(),
                        item.getQuantity(),
                        item.getEItem().getPrice(),
                        item.getEItem().getId()))
                .collect(Collectors.toList());
    }
}
