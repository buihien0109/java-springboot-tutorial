package com.example.controller_restcontroller.controller;

import com.example.controller_restcontroller.model.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentOtherController {
    // Trả về mã HTML
    @GetMapping(value = "/home", produces = {MediaType.TEXT_HTML_VALUE})
    public String getHome() {
        return "<h1 style='color: red;'>This is Home page</h1>";
    }

    // Sử dụng ResponseEntity để trả về data dạng HTML
    @GetMapping(value = "/home-other")
    public ResponseEntity<?> getHomeOther() {
        String message =  "<h1 style='color: red;'>This is Home page</h1>";
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(message);
    }

    // Trả về plain text
    @GetMapping(value = "/about", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getAbout() {
        return "This is About Page";
    }

    // Trả về Image
    @GetMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("/image/springboot.jpeg");
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }

    // Trả về json
    @GetMapping(value = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Student> getStudentsJson() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Bùi Hiên", "hien@gmail.com"));
        students.add(new Student(2, "Thu Hằng", "hang@gmail.com"));
        students.add(new Student(3, "Minh Anh", "anh@gmail.com"));
        students.add(new Student(4, "Minh Duy", "duy@gmail.com"));

        return students;
    }

    // Trả về XML
    @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public List<Student> getStudentsXml() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Bùi Hiên", "hien@gmail.com"));
        students.add(new Student(2, "Thu Hằng", "hang@gmail.com"));
        students.add(new Student(3, "Minh Anh", "anh@gmail.com"));
        students.add(new Student(4, "Minh Duy", "duy@gmail.com"));

        return students;
    }
}
