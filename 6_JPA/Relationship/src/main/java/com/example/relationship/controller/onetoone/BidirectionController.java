package com.example.relationship.controller.onetoone;

import com.example.relationship.model.one_to_one.bidrection.Card;
import com.example.relationship.model.one_to_one.bidrection.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidirectionController {

//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private CardRepository cardRepository;
//
//    @GetMapping("/students/{id}")
//    public Student getStudentById(@PathVariable Integer id) {
//        return studentRepository.getStudentById(id);
//    }
//
//    @GetMapping("/cards/{id}")
//    public Card getCardById(@PathVariable Integer id) {
//        return cardRepository.getCardById(id);
//    }
}
