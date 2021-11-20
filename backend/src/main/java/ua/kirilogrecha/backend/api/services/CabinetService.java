package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;
import ua.kirilogrecha.backend.api.services.converters.OrderConverterService;

import java.util.List;

@Service
public class CabinetService {
    private final OrderRepository orderRepository;
    private final OrderConverterService orderConverterService;


    @Autowired
    public CabinetService(OrderRepository orderRepository, OrderConverterService orderConverterService) {
        this.orderRepository = orderRepository;
        this.orderConverterService = orderConverterService;
    }

    public List<DOrder> getListByUserId(String id) {
        return orderConverterService.fromEOrderToDOrder(orderRepository.getListByUserId(id));
    }

}
