package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MessageAdapter;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.MessageResponse;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.api.service.MessageService;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.Messages;
import com.greenfox.gitinder.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Response;

import static io.fabric.sdk.android.Fabric.TAG;

public class MessagesActivity extends AppCompatActivity {
    private static final String TAG = "MessagesActivity";

    TextView usernameText;
    ImageView userPic;
    MessageAdapter messageAdapter;
    EditText editText;
    ImageButton sendButton;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    MatchService matchService;

    @Inject
    MessageService messageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_collapsing);

        usernameText = findViewById(R.id.messages_activity_username_old);
        userPic = findViewById(R.id.messages_activity_picture_old);
        sendButton = findViewById(R.id.messages_activity_send_button_old);
        editText = findViewById(R.id.messages_activity_edit_text_old);

        Match matchCurrent = getIntent().getParcelableExtra("match");
        usernameText.setText(matchCurrent.getUsername());
        Picasso.get().load(matchCurrent.getAvatarUrl()).into(userPic);

        RecyclerView recyclerView = findViewById(R.id.messages_activity_recycler_view_old);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this, messageService);
        messageAdapter.setCurrentUserUsername(sharedPreferences.getString(Constants.USERNAME, ""));

        List<Message> messageList = matchCurrent.getMessages();

        for(Message currentMessage : messageList){
            if(!currentMessage.getFrom().equals(sharedPreferences.getString(Constants.USERNAME, ""))){
                currentMessage.setFrom(usernameText.getText().toString());
            }
        }

        messageAdapter.addMessages(messageList);
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            if (editText.getText().length() > 0){
//                sendMessage(editText.getText().toString());
                sendNewMessage(editText.getText().toString(), usernameText.getText().toString());
//                if (BuildConfig.FLAVOR.equals("dev")){
//                    sendNewMessage("That is just ridiculous...", sharedPreferences.getString(Constants.USERNAME, ""), gitinderAPI, sharedPreferences);
//                }
                recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
            }
        });
    }

    public void sendNewMessage(String message, String recipient){
        gitinderAPI.sendMessage(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""),
                recipient, message).enqueue(new CustomCallback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                Log.d(TAG, "send NewMessage: onResponse: Success");
            }
        });

        gitinderAPI.messages(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""),
                recipient, 0).enqueue(new CustomCallback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                for (Match current : matchService.getMatchList()){
                    if(current.getUsername().equals(recipient)){
                        current.setMessages(response.body().getMessages());
                        messageService.setMessageList(response.body().getMessages());
                    }
                }
            }
        });

    }


//    public void sendMessage(String messageText){
//        Message newMessage = new Message(messageAdapter.getItemCount(), sharedPreferences.getString(Constants.USERNAME, ""),
//                usernameText.getText().toString(), (int)System.currentTimeMillis(), messageText);
//        messageAdapter.addMessage(newMessage);
//    }
}
