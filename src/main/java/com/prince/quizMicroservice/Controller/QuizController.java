package com.prince.quizMicroservice.Controller;


import com.prince.quizMicroservice.entity.Quiz;
import com.prince.quizMicroservice.entity.QuizDto;
import com.prince.quizMicroservice.entity.QuestionWrapper;
import com.prince.quizMicroservice.entity.Response;
import com.prince.quizMicroservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("allQuiz")
    public ResponseEntity<List<Quiz>> allQuiz(){
        return quizService.getAllQuiz();
    }
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
//        System.out.println("rached at controller layer");
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> response, @PathVariable Integer quizId){
        return quizService.submitQuiz(response, quizId);
    }
}
