package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MessageAdapter;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.MessageResponse;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.Messages;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity implements MatchService.MatchesListener {
    private static final String TAG = "MessagesActivity";

    TextView usernameText;
    ImageView userPic;
    MessageAdapter messageAdapter;
    EditText editText;
    ImageButton sendButton;

    String currentRecipient;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GitinderAPIService gitinderAPI;

    @Inject
    MatchService matchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        usernameText = findViewById(R.id.messages_activity_username_old);
        userPic = findViewById(R.id.messages_activity_picture_old);
        sendButton = findViewById(R.id.messages_activity_send_button_old);
        editText = findViewById(R.id.messages_activity_edit_text_old);

        Match matchCurrent = getIntent().getParcelableExtra("match");
        Picasso.get().load(matchCurrent.getAvatarUrl()).into(userPic);
        currentRecipient = matchCurrent.getUsername();
        usernameText.setText(currentRecipient);

        RecyclerView recyclerView = findViewById(R.id.messages_activity_recycler_view_old);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter(this, matchService, currentRecipient);

        List<Message> messageList = matchService.getMessagesByUsername(currentRecipient);

        messageAdapter.addMessages(messageList);
        recyclerView.setAdapter(messageAdapter);

        recyclerView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom){
                recyclerView.postDelayed(() -> recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1), 100);
            }
        });

        editText.setOnClickListener(v -> {
            recyclerView.postDelayed(() -> recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1), 100);
        });

        sendButton.setOnClickListener(v -> {
            if (editText.getText().length() > 0){
                sendNewMessage(editText.getText().toString(), currentRecipient);
                recyclerView.postDelayed(() -> recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1), 100);
            }
        });
    }

    public void sendNewMessage(String message, String recipient){
        gitinderAPI.provide(Constants.SEND_MESSAGE).sendMessage(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""),
                recipient, message).enqueue(new CustomCallback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                Log.d(TAG, "API.sendMessage: onResponse: Success");

                gitinderAPI.provide(Constants.GET_MESSAGES).messages(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""),
                        recipient, 0).enqueue(new CustomCallback<Messages>() {
                    @Override
                    public void onResponse(Call<Messages> call, Response<Messages> response) {
                        Log.d(TAG, "API.messages: entered onResponse");

                        matchService.setMessagesByUsername(recipient, response.body().getMessages());
                        messageAdapter.updateMessages(response.body().getMessages());
                    }
                });
            }
        });


    }

    @Override
    public void onMatchesChanged(List<Match> updatedMatches) {
        messageAdapter.updateMessages(matchService.getMessagesByUsername(currentRecipient));
    }
}
