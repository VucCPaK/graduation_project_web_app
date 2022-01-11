package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kirilogrecha.backend.api.entities.EOrderItem;

public interface OrderItemRepository extends JpaRepository<EOrderItem, String> {

}
