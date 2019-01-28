package com.greenfox.gitinder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MessageAdapter;
import com.greenfox.gitinder.model.Message;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    TextView usernameText;
    ImageView userPic;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        usernameText = findViewById(R.id.messages_activity_username);
        userPic = findViewById(R.id.messages_activity_picture);

        usernameText.setText(getIntent().getStringExtra("profileUsername"));
        Picasso.get().load(getIntent().getStringExtra("profileUrl")).into(userPic);

        RecyclerView recyclerView = findViewById(R.id.messages_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this);

        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(0,"You","",0,"Toto"));
        messageList.add(new Message(1,"Somebody","",0,"je"));
        messageList.add(new Message(2,"You","",0,"moje"));
        messageList.add(new Message(3,"Somebody","",0,"matka"));
        messageAdapter.addMessages(messageList);

//        recyclerView.setAdapter(messageAdapter);
    }

}
