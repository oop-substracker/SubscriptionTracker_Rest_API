package com.oop.substracker.repository;

import com.oop.substracker.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    List<Subscription> findByUserId(String userId);
}
