package ua.kirilogrecha.backend.api.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EPicture {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", nullable = false)
    private EItem eItem_ManyToOne;

    @Column(name = "priority", nullable = false, columnDefinition = "serial")
    private Long priority;

//    @OneToOne(mappedBy = "ePicture", cascade = CascadeType.ALL)
//    private EItem eItem_OneToOne;
}
