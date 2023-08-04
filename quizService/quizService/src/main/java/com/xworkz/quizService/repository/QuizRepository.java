package com.xworkz.quizService.repository;

import com.xworkz.quizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{
    
}
