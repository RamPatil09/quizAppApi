package com.xworkz.questionservice.controller;

import com.xworkz.questionservice.model.Question;
import com.xworkz.questionservice.model.QuestionWrapper;
import com.xworkz.questionservice.model.Response;
import com.xworkz.questionservice.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    
    @Autowired
    private QuestionServiceImpl serviceImpl;
    @Autowired
    Environment environment;


    //http://localhost:8080/question/add
    @PostMapping("add")
    public ResponseEntity<String> save(@RequestBody Question question){
        return serviceImpl.save(question);
    }

    //http://localhost:8080/question/allquestions
    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> listAll(){
        return serviceImpl.findAll();
    }

    //http://localhost:8080/question/findbyid/1
    @GetMapping("findbyid/{id}")
    public ResponseEntity<Question> findById(@PathVariable Integer id){
        return serviceImpl.findById(id);
    }

    //http://localhost:8080/question/findbycategory/java
    @GetMapping("findbycategory/{category}")
    public ResponseEntity<List<Question>> findByCategory(@PathVariable String category){
        return serviceImpl.findByCategory(category);
    }

    //http://localhost:8080/question/deletebyid/1
    @GetMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return serviceImpl.deleteById(id);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestion){
        return serviceImpl.getQuestionForQuiz(categoryName, numQuestion);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestParam List<Integer> questionId){
        System.out.println(environment.getProperty("local.server.port"));
        return serviceImpl.getQuestionFromId(questionId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return serviceImpl.getScore(responses);
    }


}
