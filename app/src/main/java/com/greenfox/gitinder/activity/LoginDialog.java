package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;

public class LoginDialog extends DialogFragment {

    private static final String TAG = "LoginDialog";

    private TextView login, exit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        login = view.findViewById(R.id.login_dialog_login);
        exit = view.findViewById(R.id.login_dialog_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="
                        +Constants.GITHUB_CLIENT_ID
                        +"&redirect_uri="+Constants.GITHUB_CALLBACK));
                getDialog().dismiss();
                startActivity(intent);
            }
        });
        return view;
    }
}
