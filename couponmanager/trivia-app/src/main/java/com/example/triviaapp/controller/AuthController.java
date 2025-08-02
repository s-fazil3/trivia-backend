package com.example.triviaapp.controller;
import com.example.triviaapp.model.User;
import com.example.triviaapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        String result = userService.registerUser(user);
        if ("User registered successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String result = userService.loginUser(user.getEmail(), user.getPassword());

        if ("Invalid credentials".equals(result)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else {
            User dbUser = userService.findByEmail(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", result);
            response.put("email", dbUser.getEmail());
            response.put("name", dbUser.getName());

            return ResponseEntity.ok(response);
        }
    }
}
