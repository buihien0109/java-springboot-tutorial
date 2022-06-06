package com.example.demo.controller;

import com.example.demo.model.Quiz;
import com.example.demo.model.QuizAnswer;
import com.example.demo.model.request.CreateQuizRequest;
import com.example.demo.model.request.UpdateQuizRequest;
import com.example.demo.service.QuizAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/v1/quizzes")
@AllArgsConstructor
public class QuizAdminController {
    private final QuizAdminService quizAdminService;

    @GetMapping()
    public ResponseEntity<?> getAllQuiz() {
        List<Quiz> quizzes = quizAdminService.getAllQuiz();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        Quiz quiz = quizAdminService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping()
    public ResponseEntity<?> createQuiz(@Valid @RequestBody CreateQuizRequest request) {
        Quiz quiz = quizAdminService.createQuiz(request);
        return ResponseEntity.ok(quiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable int id, @Valid @RequestBody UpdateQuizRequest request) {
        Quiz quiz = quizAdminService.updateQuiz(id, request);
        return ResponseEntity.ok(quiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id) {
        quizAdminService.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/quiz-answers")
    public ResponseEntity<?> createQuizAnswer(@PathVariable(name = "id") int quizId) {
        QuizAnswer quizAnswer = quizAdminService.createQuizAnswer(quizId);
        return ResponseEntity.ok(quizAnswer);
    }

    @DeleteMapping("/{id}/quiz-answers/{quizAnswerId}")
    public ResponseEntity<?> deleteQuizAnswer(@PathVariable(name = "id") int quizId,
                                              @PathVariable(name = "quizAnswerId") int quizAnswerId) {
        quizAdminService.deleteQuizAnswer(quizId, quizAnswerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
