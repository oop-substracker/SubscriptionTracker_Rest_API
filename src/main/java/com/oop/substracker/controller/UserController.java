package com.oop.substracker.controller;

import com.oop.substracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.oop.substracker.repository.UserRepository;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @PostMapping("/api/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User insertedUser = repo.insert(user);
        return ResponseEntity.ok(insertedUser);
    }

    @PostMapping("api/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            // Attempt to find a user in the repository with the provided email
            User existingUser = repo.findByEmail(user.getEmail());

            if (existingUser != null) {
                // User with the provided email exists
                // You can check the password here or perform any other authentication logic

                // For example, if you have a password field in the User entity:
                if (existingUser.getPassword().equals(user.getPassword())) {
                    // Passwords match, return a response with the existing user (successful login)
                    return ResponseEntity.ok(existingUser);
                } else {
                    // Password is incorrect, return a 401 Unauthorized status with a custom message
                    String message = "Incorrect Password";
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
                }
            } else {
                // User with the provided email not found, return a 404 Not Found status with a custom message
                String message = "User not found with email: " + user.getEmail();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (EmptyResultDataAccessException e) {
            // Handle the case when the email is not found, return a 404 Not Found status with a custom message
            String message = "User not found with email: " + user.getEmail();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }


    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = repo.findAll();
        return ResponseEntity.ok(users);
    }
}
