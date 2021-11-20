package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import ua.kirilogrecha.backend.api.services.PictureService;


@RestController
@RequestMapping("/api/picture")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    // iid - item id
    // pid - picture id

    // main picture of item is picture with the smallest priority

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/{iid}")
    public void saveImage(@PathVariable("iid") String iid, @RequestParam("files") MultipartFile[] mf) {
        pictureService.saveImageToDB(iid, mf);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/{iid}/priority")
    public void changeMainPicture(@RequestParam("firstName") String firstName,
                                  @RequestParam("secondName") String secondName) {
        pictureService.swapWeight(firstName, secondName);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/deletion")
    public void removeImage(@RequestBody String[] names) {
        pictureService.removeImageFromDB(names);
    }

    @GetMapping(value = "/{iid}/pictures/{pid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getPictureById(@PathVariable("pid") String pid) {
        return pictureService.getPictureById(pid);
    }

}
