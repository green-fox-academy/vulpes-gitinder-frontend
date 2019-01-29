package com.greenfox.gitinder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Message> messageList;

    public MessageWrapper(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageArrayList() {
        return messageList;
    }
}
