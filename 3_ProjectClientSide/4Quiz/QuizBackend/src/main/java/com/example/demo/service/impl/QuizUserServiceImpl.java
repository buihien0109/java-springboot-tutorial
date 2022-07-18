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
    private final QuizAdminService quizAdminService;

    // Lấy danh sách quiz, đảo vị trí các câu hỏi và câu trả lời
    @Override
    public List<Quiz> getAllQuiz() {
        // Lấy danh sách quiz
        List<Quiz> quizzes = quizAdminService.getAllQuiz();

        // Đảo vị trí các câu hỏi
        Collections.shuffle(quizzes);

        // Đảo vị trí các câu trả lời
        for (Quiz quiz: quizzes) {
            Collections.shuffle(quiz.getQuizAnswers());
        }
        return quizzes;
    }

    // Kiểm tra đáp án của người chơi có chính xác hay không
    @Override
    public boolean checkQuiz(int id, CheckQuizRequest request) {
        // Lấy ra câu hỏi theo id
        Quiz quiz = quizAdminService.getQuizById(id);

        // Kiểm tra câu trả lời của người chơi với câu trả lời của câu hỏi
        for (QuizAnswer qa: quiz.getQuizAnswers()) {
            if(qa.getId() == request.getId()) {
                return qa.isCorrect();
            }
        }
        return false;
    }
}
