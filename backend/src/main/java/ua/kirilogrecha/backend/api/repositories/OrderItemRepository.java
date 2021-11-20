package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kirilogrecha.backend.api.dto.DOrderItem;
import ua.kirilogrecha.backend.api.entities.EOrderItem;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<EOrderItem, String> {
    @Transactional
    @Query(value =
            "select new ua.kirilogrecha.backend.api.dto.DOrderItem(" +
                    "eOrderItem.eItem.ePicture.name, eOrderItem.eItem.name, eOrderItem.eItem.type," +
                    "eOrderItem.quantity, eOrderItem.eItem.price, eOrderItem.eItem.id) " +
                    "from EOrderItem eOrderItem where " +
                    "eOrderItem.eOrder.id = :id"
    )
    List<DOrderItem> getItemsInOrder(@Param("id") String id);
}
