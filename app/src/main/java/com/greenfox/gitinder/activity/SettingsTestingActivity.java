package com.greenfox.gitinder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.model.TestSetting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SettingsTestingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Inject
    TestSetting setting;

    private Switch getProfiles, swiping, getMatches, getMessages, sendMessage, getSettings, saveSettings;
    private List<Switch> switches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_testing);
        AndroidInjection.inject(this);
        switches = new ArrayList<>();
        getProfiles = findViewById(R.id.testing_settings_get_profiles_switch);
        getProfiles.setTag(Constants.GET_PROFILES);
        getProfiles.setOnCheckedChangeListener(this);
        getProfiles.setChecked(setting.getStatus(Constants.GET_PROFILES));
        swiping = findViewById(R.id.testing_settings_swiping_switch);
        swiping.setTag(Constants.SWIPING);
        swiping.setOnCheckedChangeListener(this);
        swiping.setChecked(setting.getStatus(Constants.SWIPING));
        getMatches = findViewById(R.id.testing_settings_get_matches_switch);
        getMatches.setTag(Constants.GET_MATCHES);
        getMatches.setOnCheckedChangeListener(this);
        getMatches.setChecked(setting.getStatus(Constants.GET_MATCHES));
        getMessages = findViewById(R.id.testing_settings_get_messages_switch);
        getMessages.setTag(Constants.GET_MESSAGES);
        getMessages.setOnCheckedChangeListener(this);
        getMessages.setChecked(setting.getStatus(Constants.GET_MESSAGES));
        sendMessage = findViewById(R.id.testing_settings_send_message_switch);
        sendMessage.setTag(Constants.SEND_MESSAGE);
        sendMessage.setOnCheckedChangeListener(this);
        sendMessage.setChecked(setting.getStatus(Constants.SEND_MESSAGE));
        getSettings = findViewById(R.id.testing_settings_get_setting_switch);
        getSettings.setTag(Constants.GET_SETTINGS);
        getSettings.setOnCheckedChangeListener(this);
        getSettings.setChecked(setting.getStatus(Constants.GET_SETTINGS));
        saveSettings = findViewById(R.id.testing_settings_save_settings_switch);
        saveSettings.setTag(Constants.SAVE_SETTINGS);
        saveSettings.setOnCheckedChangeListener(this);
        saveSettings.setChecked(setting.getStatus(Constants.SAVE_SETTINGS));
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, isChecked ? "Enabled live server for " + buttonView.getTag() + " endpoint" : buttonView.getTag() + " endpoint is now mocked", Toast.LENGTH_SHORT).show();
        setting.setStatus((String) buttonView.getTag(), isChecked);
    }
}
