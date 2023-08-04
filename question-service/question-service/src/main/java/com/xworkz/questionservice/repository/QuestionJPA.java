package com.xworkz.questionservice.repository;

import com.xworkz.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionJPA extends JpaRepository<Question, Integer>{

    List<Question> findByCategory(String category);

    @Query(value="SELECT * FROM question q where q.category=:category ORDER BY RAND() LIMIT :numofquestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer numofquestions);

}
