package com.greenfox.gitinder.model;

import java.util.List;

public class Messages {

    List<Message> message;
    int count;
    int all;

    public Messages() {
    }

    public Messages(List<Message> message, int count, int all) {
        this.message = message;
        this.count = count;
        this.all = all;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
