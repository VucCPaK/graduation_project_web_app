package mongodb.pictures.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;

import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Picture {
    @Id
    private String id;

    private Binary picture;
}