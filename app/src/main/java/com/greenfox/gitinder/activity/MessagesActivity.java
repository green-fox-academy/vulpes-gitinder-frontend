package com.greenfox.gitinder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MessageAdapter;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.MessageWrapper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    TextView usernameText;
    ImageView userPic;
    MessageAdapter messageAdapter;
    EditText editText;
    ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_old);

        usernameText = findViewById(R.id.messages_activity_username);
        userPic = findViewById(R.id.messages_activity_picture);
        sendButton = findViewById(R.id.messages_activity_send_button);
        editText = findViewById(R.id.messages_activity_edit_text);

        usernameText.setText(getIntent().getStringExtra("profileUsername"));
        Picasso.get().load(getIntent().getStringExtra("profileUrl")).into(userPic);

        RecyclerView recyclerView = findViewById(R.id.messages_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this);

        MessageWrapper messageWrapper = (MessageWrapper)getIntent().getSerializableExtra("profileMessages");
        List<Message> messageList = messageWrapper.getMessageArrayList();

        for(Message currentMessage : messageList){
            if(!currentMessage.getFrom().equals("You")){
                currentMessage.setFrom(usernameText.getText().toString());
            }
        }

        messageAdapter.addMessages(messageList);
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            if (editText.getText().length() > 0){
                sendMessage(editText.getText().toString());
                recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
            }
        });
    }

    public void sendMessage(String messageText){
        Message newMessage = new Message(messageAdapter.getItemCount(), "You",
                usernameText.getText().toString(), (int)System.currentTimeMillis(), messageText);
        messageAdapter.addMessage(newMessage);
    }

}
