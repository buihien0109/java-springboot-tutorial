package com.example.demo.model.request;

import com.example.demo.model.QuizAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizRequest {
    @NotBlank(message = "title is required")
    String title;

    @NotNull(message = "the answer is required")
    @Size(min = 2, message = "the answer must have at least 2 answers")
    List<QuizAnswer> quizAnswers;
}
