package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ua.kirilogrecha.backend.api.services.PictureService;

import java.util.List;


@RestController
@RequestMapping("/api/backend/pictures")
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
    public void saveImage(@PathVariable("iid") String iid, @RequestBody String[] nameOfPictures) {
        pictureService.saveImageToDB(iid, nameOfPictures);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/{iid}/priority")
    public void changePriorities(@PathVariable("iid") String iid, @RequestBody List<String> newPriorities) {
        pictureService.changePriorities(iid, newPriorities);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'supplier')")
    @PostMapping("/deletion")
    public void removeImage(@RequestBody String[] names) {
        pictureService.removeImageFromDB(names);
    }
}
