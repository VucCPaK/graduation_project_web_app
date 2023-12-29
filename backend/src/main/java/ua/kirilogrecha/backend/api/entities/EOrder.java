package ua.kirilogrecha.backend.api.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EOrder {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "is_sent", nullable = false)
    private boolean isSent;

    @OneToMany(mappedBy = "eOrder", fetch = FetchType.EAGER)
    private Set<EOrderItem> items;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public EOrder() {
        this.date = new Date();
        this.isSent = false;
    }
}
