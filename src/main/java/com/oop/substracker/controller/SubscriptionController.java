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
                Subscription sub = repo.insert(subscription);
                return ResponseEntity.status(HttpStatus.OK).body(sub);
            } else {
                String message = "User not authenticated";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            }

        } catch(Exception e) {
            e.printStackTrace();
            String message = "An error occurred";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }


    @PatchMapping("/api/subscriptions/update/{id}")
    public Object updateTimeStamps(@PathVariable String id, @RequestBody Subscription updatedSub) {
        try {
            Optional<Subscription> optionalSubscription = repo.findById(id);

            if (optionalSubscription.isPresent()) {
                Subscription subscription = optionalSubscription.get();
                subscription.setTimeRemaining(updatedSub.getTimeRemaining());
                subscription.setRemainingTimeInMillis(updatedSub.getRemainingTimeInMillis());
                subscription.setWindowCloseTime(updatedSub.getWindowCloseTime());

                repo.save(subscription);
                return ResponseEntity.ok("Timestamps updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String message = "An error occurred";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PostMapping("/api/subscriptions/edit/{id}")
    public Object updateTimeRemaining(@PathVariable String id) {
        try {
            Optional<Subscription> sub = repo.findById(id);

            if (sub.isPresent()) {
                Subscription subscription = sub.get();
                subscription.setTimeRemaining(Integer.MIN_VALUE);
                repo.save(subscription);
                return ResponseEntity.status(HttpStatus.OK).body("Subscription updated");
            }  else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found");
            }

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("/api/subscriptions/delete/{id}")
    public Object deleteSubscription(@PathVariable String id) {
        try {
            Optional<Subscription> sub = repo.findById(id);

            if (sub.isPresent()) {
                Subscription subscription = sub.get();
                repo.delete(subscription);
                return ResponseEntity.status(HttpStatus.OK).body("Subscription deleted");
            }  else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found");
            }

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
