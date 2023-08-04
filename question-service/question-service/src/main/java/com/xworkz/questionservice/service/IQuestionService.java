package com.xworkz.questionservice.service;

import com.xworkz.questionservice.model.Question;
import com.xworkz.questionservice.model.QuestionWrapper;
import com.xworkz.questionservice.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuestionService {

    public ResponseEntity<String> save(Question question);
    public ResponseEntity<List<Question>> findAll();
    public ResponseEntity<Question> findById(Integer id);
    public ResponseEntity<List<Question>> findByCategory(String category);
    public ResponseEntity<String> deleteById(Integer id);

    ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
