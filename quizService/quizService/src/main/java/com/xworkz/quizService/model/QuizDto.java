package com.xworkz.quizService.model;

import lombok.Data;

@Data
public class QuizDto {

    String category;
    Integer numOfQuestions;
    String title;
}
