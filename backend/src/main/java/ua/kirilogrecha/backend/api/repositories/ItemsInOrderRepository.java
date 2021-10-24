package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kirilogrecha.backend.api.entities.EItemsInOrder;

public interface ItemsInOrderRepository extends JpaRepository<EItemsInOrder, String> {
}
