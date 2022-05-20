package com.example.demo.service.impl;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Quiz;
import com.example.demo.model.QuizAnswer;
import com.example.demo.model.request.CreateQuizRequest;
import com.example.demo.model.request.UpdateQuizRequest;
import com.example.demo.service.QuizAdminService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizAdminServiceImpl implements QuizAdminService {
    private List<Quiz> quizzes = new ArrayList<>();

    public QuizAdminServiceImpl() {
        init();
    }

    @Override
    public void init() {
        Random rd = new Random();

        // Quiz 1
        List<QuizAnswer> listQuizAnswers1 = new ArrayList<>();
        listQuizAnswers1.add(new QuizAnswer(rd.nextInt(1000), "Hải Phòng", false));
        listQuizAnswers1.add(new QuizAnswer(rd.nextInt(1000), "Hà Nội", true));
        listQuizAnswers1.add(new QuizAnswer(rd.nextInt(1000), "Huế", false));
        listQuizAnswers1.add(new QuizAnswer(rd.nextInt(1000), "Thành phố Hồ Chí Minh", false));

        Quiz quiz1 = new Quiz(rd.nextInt(1000), "Thủ đô hiện nay của việt nam là gì?", listQuizAnswers1);

        // Quiz 2
        List<QuizAnswer> listQuizAnswers2 = new ArrayList<>();
        listQuizAnswers2.add(new QuizAnswer(rd.nextInt(1000), "1", false));
        listQuizAnswers2.add(new QuizAnswer(rd.nextInt(1000), "2", false));
        listQuizAnswers2.add(new QuizAnswer(rd.nextInt(1000), "3", true));
        listQuizAnswers2.add(new QuizAnswer(rd.nextInt(1000), "4", false));

        Quiz quiz2 = new Quiz(rd.nextInt(1000), "Quân Mông Nguyên sang xâm lược nước ta mấy lần", listQuizAnswers2);

        // Quiz 3
        List<QuizAnswer> listQuizAnswers3 = new ArrayList<>();
        listQuizAnswers3.add(new QuizAnswer(rd.nextInt(1000), "1910", false));
        listQuizAnswers3.add(new QuizAnswer(rd.nextInt(1000), "1911", true));
        listQuizAnswers3.add(new QuizAnswer(rd.nextInt(1000), "1912", false));
        listQuizAnswers3.add(new QuizAnswer(rd.nextInt(1000), "1913", false));

        Quiz quiz3 = new Quiz(rd.nextInt(1000), "Bác Hồ ra đi tìm đường cứu nước vào năm nào", listQuizAnswers3);

        // Quiz 4
        List<QuizAnswer> listQuizAnswers4 = new ArrayList<>();
        listQuizAnswers4.add(new QuizAnswer(rd.nextInt(1000), "Hoa hồng", false));
        listQuizAnswers4.add(new QuizAnswer(rd.nextInt(1000), "Hoa huệ", false));
        listQuizAnswers4.add(new QuizAnswer(rd.nextInt(1000), "Hoa sen", true));
        listQuizAnswers4.add(new QuizAnswer(rd.nextInt(1000), "Hoa ly", false));

        Quiz quiz4 = new Quiz(rd.nextInt(1000), "Quốc hoa của Viêt Nam là gì", listQuizAnswers4);

        // Quiz 5
        List<QuizAnswer> listQuizAnswers5 = new ArrayList<>();
        listQuizAnswers5.add(new QuizAnswer(rd.nextInt(1000), "Nghệ An", true));
        listQuizAnswers5.add(new QuizAnswer(rd.nextInt(1000), "Thanh Hóa", false));
        listQuizAnswers5.add(new QuizAnswer(rd.nextInt(1000), "Lai Châu", false));
        listQuizAnswers5.add(new QuizAnswer(rd.nextInt(1000), "Hà Nội", false));

        Quiz quiz5 = new Quiz(rd.nextInt(1000), "Tỉnh nào có diện tích lớn nhất nước ta", listQuizAnswers5);

        // Danh sach quiz
        quizzes.addAll(Arrays.asList(quiz1, quiz2, quiz3, quiz4, quiz5));
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return quizzes;
    }

    @Override
    public Quiz getQuizById(int id) {
        Optional<Quiz> quizOptional = findQuiz(id);
        if (quizOptional.isEmpty()) {
            throw new NotFoundException("quiz with id = " + id + " is not exist!");
        }

        return quizOptional.get();
    }

    @Override
    public Quiz createQuiz(CreateQuizRequest request) {
        Random rd = new Random();

        Quiz quiz = new Quiz();
        quiz.setId(rd.nextInt(1000));
        quiz.setTitle(request.getTitle());
        List<QuizAnswer> quizAnswers = new ArrayList<>();

        for (QuizAnswer quizAnswerRequest : request.getQuizAnswers()) {
            QuizAnswer quizAnswer = new QuizAnswer();
            quizAnswer.setId(rd.nextInt(1000));
            quizAnswer.setTitle(quizAnswerRequest.getTitle());
            quizAnswer.setCorrect(quizAnswerRequest.isCorrect());
            quizAnswers.add(quizAnswer);
        }

        quiz.setQuizAnswers(quizAnswers);
        quizzes.add(quiz);
        return quiz;
    }

    @Override
    public Quiz updateQuiz(int id, UpdateQuizRequest request) {
        // Kiểm tra quiz có tồn tại hay không
        Optional<Quiz> quizOptional = findQuiz(id);
        if (quizOptional.isEmpty()) {
            throw new NotFoundException("quiz with id = " + id + " is not exist!");
        }

        // Lấy ra nội dung quiz
        Quiz quiz = quizOptional.get();
        // Đặt lại title cho quiz
        quiz.setTitle(request.getTitle());
        quiz.getQuizAnswers().clear();
        quiz.setQuizAnswers(request.getQuizAnswers());

        return quiz;
    }

    @Override
    public void deleteQuiz(int id) {
        if (findQuiz(id).isEmpty()) {
            throw new NotFoundException("quiz with id = " + id + " is not exist!");
        }
        quizzes.removeIf(quiz -> quiz.getId() == id);
    }

    @Override
    public QuizAnswer createQuizAnswer(int quizId) {
        Optional<Quiz> quizOptional = findQuiz(quizId);
        if (quizOptional.isEmpty()) {
            throw new NotFoundException("quiz with id = " + quizId + " is not exist!");
        }

        Quiz quiz = quizOptional.get();

        Random rd = new Random();
        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setId(rd.nextInt(1000));
        quizAnswer.setTitle("");
        quizAnswer.setCorrect(false);

        quiz.getQuizAnswers().add(quizAnswer);

        return quizAnswer;
    }

    @Override
    public void deleteQuizAnswer(int quizId, int quizAnswerId) {
        if (findQuiz(quizId).isEmpty()) {
            throw new NotFoundException("quiz with id = " + quizId + " is not exist!");
        }

        if (findQuizAnswerById(quizId, quizAnswerId).isEmpty()) {
            throw new NotFoundException("quiz answers with id = " + quizAnswerId + " is not exist!");
        }

        findQuiz(quizId)
                .get()
                .getQuizAnswers()
                .removeIf(quizAnswer -> quizAnswer.getId() == quizAnswerId);
    }
    
    public Optional<Quiz> findQuiz(int id) {
        return quizzes
                .stream()
                .filter(quiz -> quiz.getId() == id)
                .findAny();
    }


    public Optional<QuizAnswer> findQuizAnswerById(int quizId, int quizAnswerId) {
        Quiz quiz = findQuiz(quizId).get();
        return quiz.getQuizAnswers()
                .stream()
                .filter(quizAnswer -> quizAnswer.getId() == quizAnswerId)
                .findAny();
    }
}
