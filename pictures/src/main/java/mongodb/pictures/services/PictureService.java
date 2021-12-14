package mongodb.pictures.services;

import mongodb.pictures.entities.Picture;
import mongodb.pictures.repositories.PictureRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.*;
import java.util.stream.Stream;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PictureService(PictureRepository pictureRepository, MongoTemplate mongoTemplate) {
        this.pictureRepository = pictureRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Collection<String> savePictureToDB(MultipartFile[] mf) {
        // send on backend for saving name of pictures in postgres
        ArrayList<String> result = new ArrayList<>();

        Stream.of(mf)
                .forEach(multipartFile -> {
                    Picture picture = new Picture();

                    String id = UUID.randomUUID().toString();
                    picture.setId(id);
                    result.add(id);
                    try {
                        picture.setPicture(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    pictureRepository.insert(picture);
                });

        return result;
    }

    public void removePictureFromDB(String[] ids) {
        Stream.of(ids)
                .forEach(id -> {
                    Picture p = pictureRepository.findById(id).get();
                    pictureRepository.delete(p);
                });

        //        Stream.of(ids)
        //                .forEach(pictureRepository::deleteById);
    }



    public Resource getPictureById(String pid) {
        return new InputStreamResource(
                new ByteArrayInputStream(
                        pictureRepository.findById(pid).get().getPicture().getData()
                ));
    }
}
