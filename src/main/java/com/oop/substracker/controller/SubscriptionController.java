package com.oop.substracker.controller;

import com.oop.substracker.model.User;
import com.oop.substracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oop.substracker.repository.SubscriptionRepository;
import com.oop.substracker.model.Subscription;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling subscription-related operations.
 * Manages the communication between the client and the Subscription repository.
 * Uses Spring's RestController annotation to expose endpoints for subscription functionality.
 */
@RestController
public class SubscriptionController {

    /**
     * Autowired field that injects an instance of SubscriptionRepository.
     * This field is automatically populated by the Spring Framework's dependency injection mechanism.
     * It enables access to subscription-related data operations in the application.
     */
    @Autowired
    SubscriptionRepository repo;

    @Autowired
    UserRepository user;



    /**
     * Retrieves a list of all subscriptions.
     *
     * This endpoint is mapped to the "/subscriptions" URL and handles HTTP GET requests.
     * It communicates with the Subscription repository to fetch all subscription-related data.
     *
     * @return A List of Subscription objects representing all subscriptions in the system.
     */
    @GetMapping("/api/subscriptions/all")
    public List<Subscription> getAllSubscriptions() {
        return repo.findAll();
    }

    @GetMapping("/api/subscriptions/{userId}")
    public List<Subscription> getMySubscriptions(@PathVariable String userId) {

            Optional<User> existingUser = user.findById(userId);

            if (existingUser.isPresent()) {
                return repo.findByUserId(userId);
            }

        return null;
    }

    @PostMapping("/api/subscriptions/create")
    public Object createSubscription(@RequestBody Subscription subscription) {
        try {
            String userId = subscription.getUserId();

            Optional<User> existingUser = user.findById(userId);

            if (existingUser.isPresent()) {
                Subscription sub = repo.save(subscription);
                return ResponseEntity.status(HttpStatus.OK).body(sub);
            } else {
                String message = "User not authenticated";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            }

        } catch(Exception e) {
            e.printStackTrace();
            String message = "An error occured";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

}
