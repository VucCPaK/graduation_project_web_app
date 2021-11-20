package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kirilogrecha.backend.api.entities.EOrderItem;

public interface OrderItemRepository extends JpaRepository<EOrderItem, String> {

    /*
    @Query(value =
            "select eOrderItem.eItem.ePicture.name as picture, eOrderItem.eItem.name as name, " +
                    "eOrderItem.eItem.type as type, eOrderItem.quantity as quantity, " +
                    "eOrderItem.eItem.price as price, eOrderItem.eItem.id as itemId " +
                    "from EOrderItem eOrderItem where " +
                    "eOrderItem.eOrder.id = :id"
    )
    List<DOrderItem> getItemsInOrder(@Param("id") String id);
    */
}
