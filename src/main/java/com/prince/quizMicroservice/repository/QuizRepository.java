package com.prince.quizMicroservice.repository;


import com.prince.quizMicroservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
