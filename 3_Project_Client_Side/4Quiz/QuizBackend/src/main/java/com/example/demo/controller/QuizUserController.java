package com.example.demo.controller;

import com.example.demo.model.Quiz;
import com.example.demo.model.request.CheckQuizRequest;
import com.example.demo.service.QuizUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user/api/v1/quizzes")
@AllArgsConstructor
public class QuizUserController {

    private QuizUserService quizUserService;

    @GetMapping()
    public ResponseEntity<?> getAllQuiz() {
        List<Quiz> quizzes = quizUserService.getAllQuiz();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/{id}/check")
    public ResponseEntity<?> checkQuiz(@PathVariable int id, @RequestBody CheckQuizRequest request) {
        boolean isCorrect = quizUserService.checkQuiz(id, request);
        return ResponseEntity.ok(isCorrect);
    }
}
