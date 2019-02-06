package com.greenfox.gitinder.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Message;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    TextView messageBody;

    public SentMessageHolder(View itemView){
        super(itemView);
        messageBody = itemView.findViewById(R.id.message_activity_message_body_you);
    }

    public void bind(Message message){
        messageBody.setText(message.getMessage());
    }

}
