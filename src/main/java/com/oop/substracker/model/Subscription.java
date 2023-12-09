package com.oop.substracker.model;

import com.oop.substracker.model.Subscriptions.Billing;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Subscription")
public class Subscription {
    @Id
    private String id;
    private String userId;
    private String email;
    private String platform;
    private String timeRemaining;
    private Billing billing;
    private String dueDate;

    public Subscription(String userId, String email, String platform, String timeRemaining, Billing billing, String dueDate) {
        this.userId = userId;
        this.email = email;
        this.platform = platform;
        this.timeRemaining = timeRemaining;
        this.billing = billing;
        this.dueDate = dueDate;
    }


    public Subscription() { }

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

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
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
