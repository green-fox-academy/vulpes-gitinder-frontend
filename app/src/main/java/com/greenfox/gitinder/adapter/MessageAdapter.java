package com.greenfox.gitinder.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Message> messageList;
    Context context;

    public MessageAdapter(Context context) {
        this.messageList = new ArrayList<>();
        this.context = context;
    }

    public void addMessage(Message message){
        this.messageList.add(message);
        notifyDataSetChanged();
    }

    public void addMessages(List<Message> messages){
        this.messageList.addAll(messages);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messageList.get(position);

        if (message.getFrom().equals("You")){
            convertView = messageInflater.inflate(R.layout.message_sent, null);
            holder.messageBody = convertView.findViewById(R.id.message_activity_message_body_you);
            convertView.setTag(holder);
        } else {
            convertView = messageInflater.inflate(R.layout.message_received, null);
            holder.name = convertView.findViewById(R.id.message_activity_sender_text_view);
            holder.messageBody = convertView.findViewById(R.id.message_activity_message_body_sender);
            convertView.setTag(holder);
        }

        return convertView;
    }

    public class MessageViewHolder {
        TextView name;
        TextView messageBody;
    }

}
