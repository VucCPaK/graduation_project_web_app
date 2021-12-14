package mongodb.pictures.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



//@Document(collation = "picture")
@Getter
@Setter
public class Picture {
    @Id
    private String id;

    private Binary picture;
}