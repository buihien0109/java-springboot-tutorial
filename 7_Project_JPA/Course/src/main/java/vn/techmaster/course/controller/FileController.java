package vn.techmaster.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.course.service.FileService;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    // Xem thông tin file
    @GetMapping(value = "/api/files/{fileId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] readFile(@PathVariable String fileId) {
        return fileService.readFile(fileId);
    }
}
