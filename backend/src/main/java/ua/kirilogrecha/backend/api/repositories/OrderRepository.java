package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.kirilogrecha.backend.api.dto.Period;
import ua.kirilogrecha.backend.api.entities.EOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<EOrder, String> {
    @Transactional
    @Modifying
    @Query(value = "update EOrder set isSent = :sent where id = :id")
    void updateIsSentField(@Param("id") String id, @Param("sent") boolean sent);

    @Transactional
    @Query(value = "select eOrder from EOrder eOrder where eOrder.isSent = false ")
    List<EOrder> getList();

    @Transactional
    @Query(value = "select eOrder from EOrder eOrder where eOrder.userId = :id")
    List<EOrder> getListByUserId(@Param("id") String id);

    @Transactional
    @Query(value = "select eOrder from EOrder eOrder where eOrder.date between :startDate and :endDate")
    List<EOrder> getListByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
