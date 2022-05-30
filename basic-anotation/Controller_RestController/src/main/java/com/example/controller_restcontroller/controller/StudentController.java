package com.example.controller_restcontroller.controller;

import com.example.controller_restcontroller.model.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    // Trả về mã HTML
    @RequestMapping(value = "/students/home", method = RequestMethod.GET, produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String getHome() {
        return "<h1 style='color: red;'>This is Home page</h1>";
    }

    // Trả về plain text
    @RequestMapping(value = "/students/about", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public String getAbout() {
        return "This is About Page";
    }

    // Trả về Image
    @RequestMapping(value = "/students/image", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getImage() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("/image/springboot.jpeg");
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }

    // Trả về json
    @RequestMapping(value = "/students/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Student> getStudentsJson() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Bùi Hiên", "hien@gmail.com"));
        students.add(new Student(2, "Thu Hằng", "hang@gmail.com"));
        students.add(new Student(3, "Minh Anh", "anh@gmail.com"));
        students.add(new Student(4, "Minh Duy", "duy@gmail.com"));

        return students;
    }

    // Trả về XML
    @RequestMapping(value = "/students/xml", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Student> getStudentsXml() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Bùi Hiên", "hien@gmail.com"));
        students.add(new Student(2, "Thu Hằng", "hang@gmail.com"));
        students.add(new Student(3, "Minh Anh", "anh@gmail.com"));
        students.add(new Student(4, "Minh Duy", "duy@gmail.com"));

        return students;
    }
}
