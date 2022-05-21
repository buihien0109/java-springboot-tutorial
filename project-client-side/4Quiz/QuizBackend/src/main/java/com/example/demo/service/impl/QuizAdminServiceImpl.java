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
    // Tạo danh sách quiz để quản lý
    private List<Quiz> quizzes = new ArrayList<>();

    public QuizAdminServiceImpl() {
        init();
    }

    // Thêm 1 số câu hỏi và câu trả lời ban đầu cho danh sách
    @Override
    public void init() {
        Random rd = new Random();
        // Quiz 1
        Quiz quiz1 = Quiz.builder()
                .id(1)
                .title("Thủ đô hiện nay của việt nam là gì?")
                .quizAnswers(Arrays.asList(
                        new QuizAnswer(rd.nextInt(1000), "Hải Phòng", false),
                        new QuizAnswer(rd.nextInt(1000), "Hà Nội", true),
                        new QuizAnswer(rd.nextInt(1000), "Huế", false),
                        new QuizAnswer(rd.nextInt(1000), "Thành phố Hồ Chí Minh", false)
                )).build();

        // Quiz 2
        Quiz quiz2 = Quiz.builder()
                .id(2)
                .title("Quân Mông Nguyên sang xâm lược nước ta mấy lần")
                .quizAnswers(Arrays.asList(
                        new QuizAnswer(rd.nextInt(1000), "1", false),
                        new QuizAnswer(rd.nextInt(1000), "2", false),
                        new QuizAnswer(rd.nextInt(1000), "3", true),
                        new QuizAnswer(rd.nextInt(1000), "4", false)
                )).build();

        // Quiz 3
        Quiz quiz3 = Quiz.builder()
                .id(3)
                .title("Bác Hồ ra đi tìm đường cứu nước vào năm nào")
                .quizAnswers(Arrays.asList(
                        new QuizAnswer(rd.nextInt(1000), "1910", false),
                        new QuizAnswer(rd.nextInt(1000), "1911", true),
                        new QuizAnswer(rd.nextInt(1000), "1912", false),
                        new QuizAnswer(rd.nextInt(1000), "1913", false)
                )).build();
        // Quiz 4
        Quiz quiz4 = Quiz.builder()
                .id(4)
                .title("Quốc hoa của Viêt Nam là gì")
                .quizAnswers(Arrays.asList(
                        new QuizAnswer(rd.nextInt(1000), "Hoa hồng", false),
                        new QuizAnswer(rd.nextInt(1000), "Hoa huệ", false),
                        new QuizAnswer(rd.nextInt(1000), "Hoa sen", true),
                        new QuizAnswer(rd.nextInt(1000), "Hoa ly", false)
                )).build();
        // Quiz 5
        Quiz quiz5 = Quiz.builder()
                .id(5)
                .title("Tỉnh nào có diện tích lớn nhất nước ta")
                .quizAnswers(Arrays.asList(
                        new QuizAnswer(rd.nextInt(1000), "Nghệ An", true),
                        new QuizAnswer(rd.nextInt(1000), "Thanh Hóa", false),
                        new QuizAnswer(rd.nextInt(1000), "Lai Châu", false),
                        new QuizAnswer(rd.nextInt(1000), "Hà Nội", false)
                )).build();

        // Thêm các câu hỏi vào danh sách quiz ban đầu
        quizzes.addAll(Arrays.asList(quiz1, quiz2, quiz3, quiz4, quiz5));
    }

    // Lấy danh sách quiz
    @Override
    public List<Quiz> getAllQuiz() {
        return quizzes;
    }

    // Lấy chi tiết câu hỏi theo id
    @Override
    public Quiz getQuizById(int id) {
        // Kiểm tra quiz có tồn tại hay không
        Optional<Quiz> quizOptional = findQuiz(id);
        if (quizOptional.isEmpty()) {
            throw new NotFoundException("quiz with id = " + id + " is not exist!");
        }

        return quizOptional.get();
    }

    // Tạo câu hỏi mới
    @Override
    public Quiz createQuiz(CreateQuizRequest request) {
        Random rd = new Random();

        // Tạo câu hỏi
        Quiz quiz = new Quiz();
        quiz.setId(rd.nextInt(1000));
        quiz.setTitle(request.getTitle());
        List<QuizAnswer> quizAnswers = new ArrayList<>();

        // Tạo câu trả lời
        for (QuizAnswer quizAnswerRequest : request.getQuizAnswers()) {
            QuizAnswer quizAnswer = new QuizAnswer();
            quizAnswer.setId(rd.nextInt(1000));
            quizAnswer.setTitle(quizAnswerRequest.getTitle());
            quizAnswer.setCorrect(quizAnswerRequest.isCorrect());
            quizAnswers.add(quizAnswer);
        }
        quiz.setQuizAnswers(quizAnswers);

        // Thêm câu hỏi vào list để quản lý
        quizzes.add(quiz);

        return quiz;
    }

    // Cập nhật câu hỏi
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

        // Xóa hết các phần tử trong danh sách đáp án -> sau đó set lại
        quiz.getQuizAnswers().clear();
        quiz.setQuizAnswers(request.getQuizAnswers());

        return quiz;
    }

    // Xóa câu hỏi theo id
    @Override
    public void deleteQuiz(int id) {
        // Kiểm tra quiz có tồn tại hay không
        if (findQuiz(id).isEmpty()) {
            throw new NotFoundException("quiz with id = " + id + " is not exist!");
        }

        // Nếu câu hỏi tồn tại thì tiến hành xóa
        quizzes.removeIf(quiz -> quiz.getId() == id);
    }

    // Tạo câu trả lời mới
    @Override
    public QuizAnswer createQuizAnswer(int quizId) {
        // Kiểm tra quiz có tồn tại hay không
        Optional<Quiz> quizOptional = findQuiz(quizId);
        if (quizOptional.isEmpty()) {
            throw new NotFoundException("quiz with id = " + quizId + " is not exist!");
        }

        // Lấy ra thông tin của câu hỏi
        Quiz quiz = quizOptional.get();

        // Tạo câu trả lời
        Random rd = new Random();
        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setId(rd.nextInt(1000));
        quizAnswer.setTitle("");
        quizAnswer.setCorrect(false);

        // Thêm câu trả lời mới vào danh sách câu trả lời
        quiz.getQuizAnswers().add(quizAnswer);

        // Trả về câu trả lời mới sau khi tạo thành công
        return quizAnswer;
    }

    // Xóa câu trả lời
    @Override
    public void deleteQuizAnswer(int quizId, int quizAnswerId) {
        // Kiểm tra quiz có tồn tại hay không
        if (findQuiz(quizId).isEmpty()) {
            throw new NotFoundException("quiz with id = " + quizId + " is not exist!");
        }

        // Kiểm tra câu trả lời có tồn tại hay không
        if (findQuizAnswerById(quizId, quizAnswerId).isEmpty()) {
            throw new NotFoundException("quiz answers with id = " + quizAnswerId + " is not exist!");
        }

        // Xóa câu trả lời
        findQuiz(quizId)
                .get()
                .getQuizAnswers()
                .removeIf(quizAnswer -> quizAnswer.getId() == quizAnswerId);
    }

    // HELPER METHOD : Tìm kiếm câu hỏi theo id
    public Optional<Quiz> findQuiz(int id) {
        return quizzes
                .stream()
                .filter(quiz -> quiz.getId() == id)
                .findFirst();
    }

    // HELPER METHOD : Tìm kiếm câu hỏi theo id của câu hỏi, và id của câu trả lời
    public Optional<QuizAnswer> findQuizAnswerById(int quizId, int quizAnswerId) {
        Quiz quiz = findQuiz(quizId).get();
        return quiz.getQuizAnswers()
                .stream()
                .filter(quizAnswer -> quizAnswer.getId() == quizAnswerId)
                .findFirst();
    }
}
