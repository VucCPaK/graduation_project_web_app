package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EPicture;
import ua.kirilogrecha.backend.api.exceptions.ItemNotFoundException;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.PictureRepository;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository, ItemRepository itemRepository) {
        this.pictureRepository = pictureRepository;
        this.itemRepository = itemRepository;
    }

    // save picture to folder
    @Value("${shop.storage}")
    private String PATH_TO_PICTURES;

    private String saveImageToFolder(InputStream is) throws IOException {
        // random name + extension
        final String NEW_NAME = UUID.randomUUID().toString();

        // save to directory
        ImageIO.write(ImageIO.read(is), "jpeg", new File(PATH_TO_PICTURES + NEW_NAME + ".jpeg"));

        return NEW_NAME;
    }

    private void removeImageFromFolder(String[] names) {
        Arrays.stream(names)
                .forEach(name -> {
                    try {
                        Files.delete(Paths.get(PATH_TO_PICTURES + name + ".jpeg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void swapWeight(String firstName, String secondName) {
        EPicture ePicture1 = pictureRepository.getOne(firstName);
        EPicture ePicture2 = pictureRepository.getOne(secondName);

        long temp = ePicture1.getPriority();
        ePicture1.setPriority(ePicture2.getPriority());
        ePicture2.setPriority(temp);

        pictureRepository.save(ePicture1);
        pictureRepository.save(ePicture2);
    }

    public void removeImageFromDB(String[] names) {


        Arrays.stream(names)
                .forEach(name -> {
                     EPicture ePicture = pictureRepository.getOne(name);
                     ePicture.setEItem_ManyToOne(null);
                     pictureRepository.deleteById(name);
                });

        removeImageFromFolder(names);
    }


//    public List<String> getPicturesByItemId(String id) {
//        return pictureRepository.findByItemId(id).stream()
//                .map(EPicture::getName)
//                .collect(Collectors.toList());
//    }

    public void saveImageToDB(String iid, MultipartFile[] mf) {
        if (mf.length != 0) {
            EItem eItem = itemRepository.findById(iid).orElseThrow(() -> new ItemNotFoundException(iid));

            Stream.of(mf)
                    .forEach(multipartFile -> {
                        EPicture ePicture = new EPicture();

                        try {
                            ePicture.setName(saveImageToFolder(multipartFile.getInputStream()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ePicture.setEItem_ManyToOne(eItem);

                        // ePicture - updated
//                        ePicture = pictureRepository.save(ePicture);

                        eItem.getPictures().add(ePicture);

                        itemRepository.save(eItem);
                    });
        }
    }

    public Resource getPictureById(String pid) {
        return new FileSystemResource(PATH_TO_PICTURES + pid + ".jpeg");
    }
}
