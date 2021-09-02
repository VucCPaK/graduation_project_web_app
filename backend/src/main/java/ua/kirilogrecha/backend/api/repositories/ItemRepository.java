package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.kirilogrecha.backend.api.entities.EItem;

public interface ItemRepository extends JpaRepository<EItem, String> {
    @Transactional
    @Modifying
    @Query(value = "update EItem set amount = amount - :quantity where id = :id")
    void updateAmount(@Param("id") String id, @Param("quantity") int quantity);
}
