package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kirilogrecha.backend.api.entities.EPicture;

import java.util.List;

public interface PictureRepository extends JpaRepository<EPicture, String> {
    @Query(value = "from EPicture where eItem_ManyToOne.id = :iid")
    List<EPicture> findByItemId(@Param("iid") String iid);
}
