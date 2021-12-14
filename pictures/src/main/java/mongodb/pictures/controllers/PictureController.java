package mongodb.pictures.controllers;

import mongodb.pictures.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/api/mongo/pictures")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping()
    public Collection<String> saveImage(@RequestParam("files") MultipartFile[] mf) {
        return pictureService.savePictureToDB(mf);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/deletion")
    //@DeleteMapping Mapping(value = "")
    public void removeImage(@RequestBody String[] names) {
        pictureService.removePictureFromDB(names);
    }

    // pid - picture id
    @GetMapping(value = "/{pid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getPictureById(@PathVariable("pid") String pid) {
        return pictureService.getPictureById(pid);
    }
}
