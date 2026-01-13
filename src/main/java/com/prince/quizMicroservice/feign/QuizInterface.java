package com.prince.quizMicroservice.feign;

import com.prince.quizMicroservice.entity.QuestionWrapper;
import com.prince.quizMicroservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTIONMICROSERVICE")
public interface QuizInterface {
    @GetMapping("question/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
