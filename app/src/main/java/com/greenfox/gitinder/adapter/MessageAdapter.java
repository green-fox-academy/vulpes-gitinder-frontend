package com.greenfox.gitinder.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.holder.ReceivedMessageHolder;
import com.greenfox.gitinder.holder.SentMessageHolder;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessageAdapter extends RecyclerView.Adapter {

    String currentUserUsername;
    Context context;
    List<Message> messageList;

    MatchService matchService;

    public MessageAdapter(Context context, MatchService matchService) {
        this.context = context;
        this.messageList = new ArrayList<>();
        this.matchService = matchService;
    }

    @Override
    public int getItemViewType(int position) {
        Message currentMessage = messageList.get(position);

        if(currentMessage.getFrom().equals(currentUserUsername)){
            return Constants.SENT_MESSAGE;
        } else {
            return Constants.RECEIVED_MESSAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if(i == Constants.SENT_MESSAGE){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_sent, viewGroup, false);
            return new SentMessageHolder(view);
        } else if (i == Constants.RECEIVED_MESSAGE){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.message_received, viewGroup, false);
            return new ReceivedMessageHolder(view);
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = messageList.get(i);

        switch (viewHolder.getItemViewType()){
            case Constants.SENT_MESSAGE:
                ((SentMessageHolder) viewHolder).bind(message);
                break;
            case Constants.RECEIVED_MESSAGE:
                ((ReceivedMessageHolder) viewHolder).bind(message);
        }

    }

    public void addMessages(List<Message> messages){
        this.messageList.addAll(messages);
        matchService.updateNewMatchesCount();
        notifyDataSetChanged();
    }

    public void addMessage(Message message){
        this.messageList.add(message);

        // Should probably call the backend here.
        for (Match current : matchService.getMatchList()){
            if(current.getUsername().equals(currentUserUsername)){
                current.getMessages().add(message);
                matchService.updateNewMatchesCount();
            }
        }
        //

        notifyDataSetChanged();
    }

    public void setCurrentUserUsername(String currentUserUsername) {
        this.currentUserUsername = currentUserUsername;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


}
