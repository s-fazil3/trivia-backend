package com.example.triviaapp.service;

import com.example.triviaapp.model.Question;
import com.example.triviaapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class Questionservice {

    @Autowired
    private QuestionRepository questionRepo;

    public List<Question> getRandomQuestions(int count) {
        List<Question> allQuestions = questionRepo.findAll();
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(count).toList();
    }

    public int calculateScore(List<Useranswer> userAnswers) {
        int score = 0;
        for (Useranswer ans : userAnswers) {
            Question q = questionRepo.findById(ans.getQuestionId()).orElse(null);
            if (q != null && q.getCorrectOption().equalsIgnoreCase(ans.getSelectedOption())) {
                score++;
            }
        }
        return score;
    }
}
