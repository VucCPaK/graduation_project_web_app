package ua.kirilogrecha.backend.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items_in_order")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EOrderItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "item_id",nullable = false)
    private String itemId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private EOrder eOrder;
}
