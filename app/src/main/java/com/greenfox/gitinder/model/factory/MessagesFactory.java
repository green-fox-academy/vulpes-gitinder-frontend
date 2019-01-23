package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesFactory {
    public static List<Message> createMessages(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(0, "me", "you", 0, "Hello"));
        messages.add(new Message(0, "me", "you", 0, "HALLO"));
        messages.add(new Message(0, "me", "you", 0, "Beer"));
        return messages;
    }
}
