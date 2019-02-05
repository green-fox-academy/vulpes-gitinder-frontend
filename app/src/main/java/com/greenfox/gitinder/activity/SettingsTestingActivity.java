package com.greenfox.gitinder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import com.greenfox.gitinder.R;

public class SettingsTestingActivity extends AppCompatActivity {

    private Switch getProfiles, swiping, getMatches, getMessages, sendMessage, getSettings, saveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_testing);
        getProfiles = findViewById(R.id.testing_settings_get_profiles_switch);
        swiping = findViewById(R.id.testing_settings_swiping_switch);
        getMatches = findViewById(R.id.testing_settings_get_matches_switch);
        getMessages = findViewById(R.id.testing_settings_get_messages_switch);
        sendMessage = findViewById(R.id.testing_settings_send_message_switch);
        getSettings = findViewById(R.id.testing_settings_get_setting_switch);
        saveSettings = findViewById(R.id.testing_settings_save_settings_switch);
    }

}
