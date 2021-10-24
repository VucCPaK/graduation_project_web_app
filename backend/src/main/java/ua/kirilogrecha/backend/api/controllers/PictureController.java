package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ua.kirilogrecha.backend.api.services.PictureService;

import java.io.*;
import java.util.List;


@RestController
@RequestMapping("/api/picture")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{iid}/pictures")
    public List<String> getPicturesByItemId(@PathVariable("iid") String iid) {
        return pictureService.getPicturesByItemId(iid);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/{iid}/pictures")
    public String saveImage(@PathVariable("iid") String iid, @RequestParam("file") MultipartFile mf) throws IOException {
        return pictureService.saveImage(iid, mf);
    }

    @GetMapping(value = "/{iid}/pictures/{pid}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getPictureById(@PathVariable("pid") String pid) {
        return pictureService.getPictureById(pid);
    }

}
