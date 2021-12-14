package ua.kirilogrecha.backend.api.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "items")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "description", nullable = false)
    private String description;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "main_picture")
//    private EPicture ePicture;

    @OneToMany(mappedBy = "eItem_ManyToOne", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<EPicture> pictures;

    @OneToMany(mappedBy = "eItem", cascade = CascadeType.ALL)
    private Collection<EOrderItem> eOrderItems;

    @Column(name = "categories", nullable = false)
    private String categories;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "supplier", nullable = false)
    private String supplierId;
}