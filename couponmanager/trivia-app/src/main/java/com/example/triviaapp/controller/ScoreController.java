package com.example.triviaapp.controller;
import jakarta.servlet.http.HttpServletRequest;
import com.example.triviaapp.util.JwtUtil;
import com.example.triviaapp.model.Score;
import com.example.triviaapp.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "http://localhost:5173")
public class ScoreController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ScoreRepository scoreRepository;

    @PostMapping("/save")
    public Score saveScore(@RequestBody Score score, HttpServletRequest request) {
        String email = jwtUtil.extractEmailFromRequest(request);
        score.setEmail(email); // Overwrite any spoofed email from frontend
        return scoreRepository.save(score);
    }

    @GetMapping("/history")
    public List<Score> getScoreHistory() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return scoreRepository.findByEmail(email);
    }


}
