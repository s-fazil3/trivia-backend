package com.example.triviaapp.controller;

import com.example.triviaapp.model.Question;
import com.example.triviaapp.service.Questionservice;
import com.example.triviaapp.service.Useranswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/trivia")
public class Questioncontroller {

    @Autowired
    private Questionservice questionService;

    @GetMapping("/random")
    public ResponseEntity<List<Question>> getRandomQuestions(@RequestParam(defaultValue = "5") int count) {
        return ResponseEntity.ok(questionService.getRandomQuestions(count));
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitAnswers(@RequestBody List<Useranswer> answers) {
        int score = questionService.calculateScore(answers);
        return ResponseEntity.ok(score);
    }
}
