package mongodb.pictures.repositories;

import mongodb.pictures.entities.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PictureRepository extends MongoRepository<Picture, String> {

}
