package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EPicture;
import ua.kirilogrecha.backend.api.exceptions.ItemNotFoundException;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.PictureRepository;

import java.util.*;
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

//    // save picture to folder
//    @Value("${shop.storage}")
//    private String PATH_TO_PICTURES;

    // iid - item id
    public void changePriorities(String iid, List<String> newPriorities) {
        List<Long> picturesPriority = itemRepository
                .getOne(iid)
                .getPictures().stream()
                .sorted(Comparator.comparingLong(EPicture::getPriority))
                .map(EPicture::getPriority)
                .collect(Collectors.toList());

        // swap priority
        for (int i = 0; i < newPriorities.size(); i++) {
            EPicture pic = pictureRepository.getOne(newPriorities.get(i));
            pic.setPriority(picturesPriority.get(i));
            pictureRepository.save(pic);

        }
    }

    public void removeImageFromDB(String[] names) {

        Arrays.stream(names)
                .forEach(name -> {
                     EPicture ePicture = pictureRepository.getOne(name);
                     ePicture.setEItem_ManyToOne(null);
                     pictureRepository.deleteById(name);
                });

//        removeImageFromFolder(names);
    }

    public void saveImageToDB(String iid, String[] nameOfPictures) {
        if (nameOfPictures.length != 0) {
            EItem eItem = itemRepository.findById(iid).orElseThrow(() -> new ItemNotFoundException(iid));

            Arrays.stream(nameOfPictures)
                    .forEach(name -> {
                        EPicture ePicture = new EPicture();
                        ePicture.setName(name);
                        ePicture.setEItem_ManyToOne(eItem);
                        eItem.getPictures().add(ePicture);

                        itemRepository.save(eItem);
                    });
        }
    }
}
