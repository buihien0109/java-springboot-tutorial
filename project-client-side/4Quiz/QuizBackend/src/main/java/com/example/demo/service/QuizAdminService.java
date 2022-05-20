package com.example.demo.service;

import com.example.demo.model.Quiz;
import com.example.demo.model.QuizAnswer;
import com.example.demo.model.request.CreateQuizRequest;
import com.example.demo.model.request.UpdateQuizRequest;

import java.util.List;

public interface QuizAdminService {
    void init();

    List<Quiz> getAllQuiz();

    Quiz getQuizById(int id);

    Quiz createQuiz(CreateQuizRequest request);

    Quiz updateQuiz(int id, UpdateQuizRequest request);

    void deleteQuiz(int id);

    // Quiz Answer
    QuizAnswer createQuizAnswer(int quizId);

    void deleteQuizAnswer(int quizId, int quizAnswerId);
}
