package com.greenfox.gitinder.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Message;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageBody;
    TextView senderName;

    public ReceivedMessageHolder(View itemView){
        super(itemView);
        messageBody = itemView.findViewById(R.id.message_activity_message_body_sender);
        senderName = itemView.findViewById(R.id.message_activity_sender_text_view);
    }

    public void bind(Message message){
        messageBody.setText(message.getMessage());
        senderName.setText(message.getFrom());
    }

}
