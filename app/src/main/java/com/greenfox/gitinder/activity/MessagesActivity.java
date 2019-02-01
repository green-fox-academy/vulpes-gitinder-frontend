package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MessageAdapter;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MessagesActivity extends AppCompatActivity {

    TextView usernameText;
    ImageView userPic;
    MessageAdapter messageAdapter;
    EditText editText;
    ImageButton sendButton;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_old);

        usernameText = findViewById(R.id.messages_activity_username_old);
        userPic = findViewById(R.id.messages_activity_picture_old);
        sendButton = findViewById(R.id.messages_activity_send_button_old);
        editText = findViewById(R.id.messages_activity_edit_text_old);

        Match matchCurrent = getIntent().getParcelableExtra("match");
        usernameText.setText(matchCurrent.getUsername());
//        usernameText.setText(getIntent().getStringExtra("profileUsername"));
        Picasso.get().load(matchCurrent.getAvatarUrl()).into(userPic);
//        Picasso.get().load(getIntent().getStringExtra("profileUrl")).into(userPic);



        RecyclerView recyclerView = findViewById(R.id.messages_activity_recycler_view_old);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this);
        messageAdapter.setCurrentUserUsername(sharedPreferences.getString(Constants.USERNAME, ""));

        List<Message> messageList = matchCurrent.getMessages();
//        MessageWrapper messageWrapper = (MessageWrapper)getIntent().getSerializableExtra("profileMessages");
//        List<Message> messageList = messageWrapper.getMessageArrayList();

        for(Message currentMessage : messageList){
            if(!currentMessage.getFrom().equals(sharedPreferences.getString(Constants.USERNAME, ""))){
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
        Message newMessage = new Message(messageAdapter.getItemCount(), sharedPreferences.getString(Constants.USERNAME, ""),
                usernameText.getText().toString(), (int)System.currentTimeMillis(), messageText);
        messageAdapter.addMessage(newMessage);
    }

}
