package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.model.Message;

public class MessageResponse {
    String status;
    Message message;

    public MessageResponse() {
    }

    public MessageResponse(String status, Message message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
