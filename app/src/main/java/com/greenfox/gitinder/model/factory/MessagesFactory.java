package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesFactory {
    public static List<Message> createMessages(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(0, "You", "dwgasfg", 0, "Hello from the other side."));
        messages.add(new Message(1, "LOOL", "you", 0, "Very long afternoon."));
        messages.add(new Message(2, "You", "gdfssfhs", 0, "Majestic cup holder."));
        messages.add(new Message(3, "LOOsafasdgvaL", "you", 0, "Lava bunjee jumping."));
        messages.add(new Message(4, "You", "dsgbsfb", 0, "Electrifying experiences."));
        messages.add(new Message(5, "LOOADGBASDUOBPEL", "you", 0, "Once pony, once horse."));
        messages.add(new Message(6, "You", "sfsdfbsf", 0, "All applies are liars."));
        messages.add(new Message(6, "ASVADVEGQGH", "you", 0, "Amazing dog."));
        return messages;
    }

    public static List<Message> createEmptyMessages(){
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    public static List<Message> createMessage(){
        List<Message> message = new ArrayList<>();
        message.add(new Message(0, "me", "you", 0, "What is love?"));
        return message;
    }
}
