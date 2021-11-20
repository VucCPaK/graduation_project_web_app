package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private String savePicture(InputStream is) throws IOException {
        // random name + extension
        final String NEW_NAME = UUID.randomUUID().toString();

        // save to directory
        ImageIO.write(ImageIO.read(is), "jpeg", new File(PATH_TO_PICTURES + NEW_NAME  + ".jpeg"));

        return NEW_NAME;
    }


    public List<String> getPicturesByItemId(String id) {
        return pictureRepository.findByItemId(id).stream()
                .map(EPicture::getName)
                .collect(Collectors.toList());
    }

    public String saveImage(String iid, MultipartFile mf) throws IOException {
        EItem eItem = itemRepository.findById(iid).orElseThrow(() -> new ItemNotFoundException(iid));

        EPicture ePicture = new EPicture();
        String id;

        ePicture.setName((id = savePicture(mf.getInputStream())));
        ePicture.setEItem_ManyToOne(eItem);
        ePicture.setEItem_OneToOne(eItem);

        if (eItem.getEPicture() == null)
            eItem.setEPicture(ePicture);

        pictureRepository.save(ePicture);
        itemRepository.save(eItem);

        return id;
    }

    public Resource getPictureById(String pid) {
        return new FileSystemResource(PATH_TO_PICTURES + pid + ".jpeg");
    }
}
