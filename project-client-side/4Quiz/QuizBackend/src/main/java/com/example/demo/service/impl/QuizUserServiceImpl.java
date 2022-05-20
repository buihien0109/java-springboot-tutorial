package com.example.demo.service.impl;

import com.example.demo.model.Quiz;
import com.example.demo.model.QuizAnswer;
import com.example.demo.model.request.CheckQuizRequest;
import com.example.demo.service.QuizAdminService;
import com.example.demo.service.QuizUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizUserServiceImpl implements QuizUserService {
    private QuizAdminService quizAdminService;

    @Override
    public List<Quiz> getAllQuiz() {
        List<Quiz> quizzes = quizAdminService.getAllQuiz();
        Collections.shuffle(quizzes);
        for (Quiz quiz: quizzes) {
            Collections.shuffle(quiz.getQuizAnswers());
        }
        return quizzes;
    }

    @Override
    public boolean checkQuiz(int id, CheckQuizRequest request) {
        Quiz quiz = quizAdminService.getQuizById(id);
        for (QuizAnswer qa: quiz.getQuizAnswers()) {
            if(qa.getId() == request.getId()) {
                return qa.isCorrect();
            }
        }
        return false;
    }
}
