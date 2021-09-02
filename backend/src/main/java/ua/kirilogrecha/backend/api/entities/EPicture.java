package ua.kirilogrecha.backend.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "picture")
@Getter
@Setter
public class EPicture {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private EItem eItem_ManyToOne;

    @OneToOne(mappedBy = "ePicture")
    private EItem eItem_OneToOne;
}
