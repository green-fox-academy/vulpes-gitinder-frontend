package com.greenfox.gitinder.modelBANANA;

import com.google.gson.annotations.SerializedName;

public class Message {

    int id;
    String from;
    String to;

    @SerializedName("created_at")
    int createdAt;

    String message;

    public Message() {
    }

    public Message(int id, String from, String to, int createdAt, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.createdAt = createdAt;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
