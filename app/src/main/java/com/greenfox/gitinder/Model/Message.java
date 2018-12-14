package com.greenfox.gitinder.Model;

public class Message {

    int id;
    String from;
    String to;
    int created_at;
    String message;

    public Message() {
    }

    public Message(int id, String from, String to, int created_at, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.created_at = created_at;
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

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
