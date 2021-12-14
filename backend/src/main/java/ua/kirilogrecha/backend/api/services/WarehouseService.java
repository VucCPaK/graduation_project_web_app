package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DOrderItem;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.entities.EPicture;
import ua.kirilogrecha.backend.api.repositories.OrderItemRepository;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;
import ua.kirilogrecha.backend.api.services.converters.OrderConverterService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    private final OrderRepository orderRepository;
    private final OrderConverterService orderConverterService;

    @Autowired
    public WarehouseService(OrderRepository orderRepository,
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
                .getOne(id)
                .getItems()
                .stream()
                .map(item -> new DOrderItem(
                        item.getEItem().getName(),
                        item.getEItem().getType(),
                        item.getQuantity(),
                        item.getEItem().getPrice(),
                        item.getEItem().getId(),
                        item.getEItem().getPictures().stream()
                                .sorted(Comparator.comparingLong(EPicture::getPriority))
                                .map(EPicture::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
