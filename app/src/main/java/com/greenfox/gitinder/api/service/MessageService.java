package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    List<Message> messageList;
    MessagesListener messagesListener;

    public MessageService() {
        this.messageList = new ArrayList<>();
    }

    public void updateMessages(){
        messagesListener.onMessagesChanged(messageList);
    }

    public void addMessages(List<Message> messageList){
        messageList.addAll(messageList);
        updateMessages();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        updateMessages();
    }

    public void setMessagesListener(MessagesListener messagesListener) {
        this.messagesListener = messagesListener;
    }

    public interface MessagesListener{
        public void onMessagesChanged(List<Message> updatedMessages);
    }
}
