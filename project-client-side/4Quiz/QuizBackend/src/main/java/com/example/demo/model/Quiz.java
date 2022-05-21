package com.example.demo.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    private int id;
    private String title;
    private List<QuizAnswer> quizAnswers;
}
