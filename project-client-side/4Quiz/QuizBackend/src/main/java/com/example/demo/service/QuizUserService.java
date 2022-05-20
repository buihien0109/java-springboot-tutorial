package com.example.demo.service;

import com.example.demo.model.Quiz;
import com.example.demo.model.request.CheckQuizRequest;

import java.util.List;

public interface QuizUserService {
    List<Quiz> getAllQuiz();
    boolean checkQuiz(int id, CheckQuizRequest request);
}
