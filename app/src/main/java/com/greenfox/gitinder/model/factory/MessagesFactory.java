package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesFactory {

    public static List<Message> createMessages(String currentUserUsername) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1, "LOOL", currentUserUsername, 0, "Very long afternoon."));
        messages.add(new Message(2, currentUserUsername, "gdfssfhs", 0, "Majestic cup holder."));
        messages.add(new Message(3, "LOOsafasdgvaL", currentUserUsername, 0, "Lava bunjee jumping."));
        messages.add(new Message(4, currentUserUsername, "dsgbsfb", 0, "Ponies are weird."));
        messages.add(new Message(5, "LOOADGBASDUOBPEL", currentUserUsername, 0, "Once pony, once horse."));
        messages.add(new Message(6, currentUserUsername, "sfsdfbsf", 0, "All apples are a lie."));
        messages.add(new Message(7, "ASVADVEGQGH", currentUserUsername, 0, "Amazing dog."));
        messages.add(new Message(8, currentUserUsername, "gdfssfhs", 0, "Giraffe."));
        messages.add(new Message(9, "LOOsafasdgvaL", currentUserUsername, 0, "Lava bunjee jumping."));
        messages.add(new Message(10, currentUserUsername, "dsgbsfb", 0, "Segway."));
        messages.add(new Message(11, "LOOADGBASDUOBPEL", currentUserUsername, 0, "Once pony, always pony."));
        messages.add(new Message(12, currentUserUsername, "sfsdfbsf", 0, "Magic."));
        messages.add(new Message(13, "ASVADVEGQGH", currentUserUsername, 0, "Very secure message."));
        return messages;
    }

    public static Message createNewMessage() {
        return new Message(0, "me", "you", 0, "Frantisek");
    }

    public static Message createNewMessageWithText(String messageText){
        return new Message(0, "me", "you", 0, messageText);
    }

    public static List<Message> createEmptyMessages() {
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    public static List<Message> createMessage() {
        List<Message> message = new ArrayList<>();
        message.add(new Message(0, "me", "you", 0, "What is love?"));
        return message;
    }
}
