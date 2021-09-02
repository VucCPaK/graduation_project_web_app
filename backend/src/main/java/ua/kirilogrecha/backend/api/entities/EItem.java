package ua.kirilogrecha.backend.api.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_picture", referencedColumnName = "name")
    private EPicture ePicture;

    @OneToMany(mappedBy = "eItem_ManyToOne", fetch = FetchType.EAGER)
    private List<EPicture> pictures;

    @Column(name = "categories", nullable = false)
    private String categories;

    @Column(name = "type", nullable = false)
    private String type;
}