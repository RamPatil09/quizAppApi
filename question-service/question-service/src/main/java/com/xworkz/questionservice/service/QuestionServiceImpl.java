package com.xworkz.questionservice.service;

import com.xworkz.questionservice.model.Question;
import com.xworkz.questionservice.model.QuestionWrapper;
import com.xworkz.questionservice.model.Response;
import com.xworkz.questionservice.repository.QuestionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService{

    @Autowired
    private QuestionJPA questionJPA;

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionJPA.findRandomQuestionsByCategory(categoryName, numQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> save(Question question) {
        questionJPA.save(question);
        try{
        return new ResponseEntity<String>( "Successfully saved!",HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<String>( "Something went wrong!",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> findAll() {
        try{
            return new ResponseEntity<List<Question>>(questionJPA.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<List<Question>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Question> findById(Integer id) {
        try{
            Question question = questionJPA.findById(id).get();
                return new ResponseEntity<Question>(question,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }

        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND );
    }

    @Override
    public ResponseEntity<List<Question>> findByCategory(String category) {
        try{
            List<Question> question = questionJPA.findByCategory(category);
            return new ResponseEntity<List<Question>>(question, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<List<Question>>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        try{
            if (questionJPA.existsById(id)){
                questionJPA.deleteById(id);
                return new ResponseEntity<String>("Deleted record with ID : "+id,HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Deleted record with ID : "+id,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Integer id : questionId){
            questions.add(questionJPA.findById(id).get());
        }

        for (Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion(question.getQuestion());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            questionWrappers.add(wrapper);
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for (Response response : responses) {
            Question question = questionJPA.findById(response.getId()).get();
            if (response.getResponse().equals(question.getAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
