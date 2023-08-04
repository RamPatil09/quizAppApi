package com.xworkz.quizService.controller;

import com.xworkz.quizService.model.QuestionWrapper;
import com.xworkz.quizService.model.QuizDto;
import com.xworkz.quizService.model.Response;
import com.xworkz.quizService.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private IQuizService quizService;
    
    //http://localhost:8080/quiz/create?category=java&numofquestions=5&title=javaquiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(),quizDto.getTitle());
    }

    //http://localhost:8080/quiz/get/1
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForUser(@PathVariable Integer id){
        return quizService.getQuestions(id);
    }

    //http://localhost:8080/quiz/submit/1
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitAnswer(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateCorrectAns(id, responses);
    }

}
