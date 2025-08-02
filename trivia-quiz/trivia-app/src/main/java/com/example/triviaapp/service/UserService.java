package com.example.triviaapp.service;
import com.example.triviaapp.model.User;
import com.example.triviaapp.repository.UserRepository;
import com.example.triviaapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully";
    }
    public String loginUser(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "Invalid credentials";
        }

        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(email, user.getName());
        } else {
            return "Invalid credentials";
        }
    }
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}
