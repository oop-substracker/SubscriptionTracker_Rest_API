package com.oop.substracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oop.substracker.repository.SubscriptionRepository;
import com.oop.substracker.model.Subscription;

import java.util.List;

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


    /**
     * Retrieves a list of all subscriptions.
     *
     * This endpoint is mapped to the "/subscriptions" URL and handles HTTP GET requests.
     * It communicates with the Subscription repository to fetch all subscription-related data.
     *
     * @return A List of Subscription objects representing all subscriptions in the system.
     */
    @GetMapping("/api/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return repo.findAll();
    }


}
