package com.xworkz.quizService.service;

import com.xworkz.quizService.model.QuestionWrapper;
import com.xworkz.quizService.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuizService {

    ResponseEntity<String> createQuiz(String category, Integer numofquestions, String title);

    ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id);

    ResponseEntity<Integer> calculateCorrectAns(Integer id, List<Response> responses);

}
