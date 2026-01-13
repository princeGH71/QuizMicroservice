package com.prince.quizMicroservice.service;

import com.prince.quizMicroservice.entity.Quiz;
import com.prince.quizMicroservice.entity.QuestionWrapper;
import com.prince.quizMicroservice.entity.Response;
import com.prince.quizMicroservice.feign.QuizInterface;
import com.prince.quizMicroservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//        System.out.println("line 1");
        List<Integer> questionNums = quizInterface.generateQuiz(category, numQ).getBody();
//        System.out.println("line 3 we got our qid "+questionNums);
        Quiz quiz = new Quiz();
//        System.out.println("line 3 we got our qid we are at line 4 "+questionNums);
        quiz.setTitle(title);
//        System.out.println("line 5 title is  "+quiz.getTitle());
        quiz.setQuestions(questionNums);
//        System.out.println("line 6 question is  "+quiz.getQuestions());
        Quiz qz= quizRepository.save(quiz);
//        System.out.println("line 7 and quiz has been created with id  "+qz.getId());
        Quiz qz1 = quizRepository.findById(qz.getId()).get();
//        System.out.println("line8 and quizrepo with this quiz id has these question nums" + qz1.getQuestions());
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {
        List<Quiz> allQuiz = quizRepository.findAll();
        return new ResponseEntity<>(allQuiz, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionlist = quiz.getQuestions();
        List<QuestionWrapper> questionsForUser = quizInterface.getQuestionFromId(questionlist).getBody();

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(List<Response> response, Integer quizId) {
        Integer result = quizInterface.getScore(response).getBody();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
