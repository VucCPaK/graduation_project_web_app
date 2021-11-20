package ua.kirilogrecha.backend.api.services.converters;

import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DOrder;
import ua.kirilogrecha.backend.api.entities.EOrder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderConverterService {
    public OrderConverterService() {
    }

    public DOrder fromEOrderToDOrder(EOrder eOrder) {
        var dOrder = new DOrder();

        dOrder.setId(eOrder.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        dOrder.setDate(simpleDateFormat.format(eOrder.getDate()));
        dOrder.setTotalPrice(eOrder.getTotalPrice());
        dOrder.setSent(eOrder.isSent());

        return dOrder;
    }

    public List<DOrder> fromEOrderToDOrder(List<EOrder> eOrders) {
        return eOrders.stream()
                .map(this::fromEOrderToDOrder)
                .collect(Collectors.toList());
    }

}
