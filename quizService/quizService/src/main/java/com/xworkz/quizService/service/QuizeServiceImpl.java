package com.xworkz.quizService.service;

import com.xworkz.quizService.feign.QuizInterface;
import com.xworkz.quizService.model.QuestionWrapper;
import com.xworkz.quizService.model.Quiz;
import com.xworkz.quizService.model.Response;
import com.xworkz.quizService.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizeServiceImpl implements IQuizService{

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    QuizInterface quizInterface;

    @Override
    public ResponseEntity<String> createQuiz(String category, Integer numofquestions, String title) {

    List<Integer> questions = quizInterface.getQuestionForQuiz(category,numofquestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<String>("Successfully Created!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionId = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionId);
        return questions;
    }

    @Override
    public ResponseEntity<Integer> calculateCorrectAns(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
    
}
