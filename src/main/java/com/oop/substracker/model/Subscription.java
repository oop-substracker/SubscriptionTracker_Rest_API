package com.oop.substracker.model;

import com.oop.substracker.model.Subscriptions.Billing;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Subscription")
public class Subscription {
    @Id
    private String id;
    private String userId;
    private String email;
    private String platform;
    private long timeRemaining;
    private Billing billing;
    private String dueDate;

    private long remainingTimeInMillis;
    private Instant windowCloseTime;

    public Subscription(String userId, String email, String platform, long timeRemaining, Billing billing, String dueDate) {
        this.userId = userId;
        this.email = email;
        this.platform = platform;
        this.timeRemaining = timeRemaining;
        this.billing = billing;
        this.dueDate = dueDate;
    }


    public Subscription() { }

    public long getRemainingTimeInMillis() {
        return remainingTimeInMillis;
    }

    public void setRemainingTimeInMillis(long remainingTimeInMillis) {
        this.remainingTimeInMillis = remainingTimeInMillis;
    }

    public Instant getWindowCloseTime() {
        return windowCloseTime;
    }

    public void setWindowCloseTime(Instant windowCloseTime) {
        this.windowCloseTime = windowCloseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
