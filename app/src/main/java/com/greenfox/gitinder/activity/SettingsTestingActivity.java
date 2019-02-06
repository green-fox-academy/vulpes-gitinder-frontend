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
        getProfiles.setTag(Constants.GET_PROFILES_ENDPOINT);
        getProfiles.setOnCheckedChangeListener(this);
        getProfiles.setChecked(setting.isGetProfiles());
        swiping = findViewById(R.id.testing_settings_swiping_switch);
        swiping.setTag(Constants.SWIPING_ENDPOINT);
        swiping.setOnCheckedChangeListener(this);
        swiping.setChecked(setting.isSwiping());
        getMatches = findViewById(R.id.testing_settings_get_matches_switch);
        getMatches.setTag(Constants.GET_MATCHES_ENDPOINT);
        getMatches.setOnCheckedChangeListener(this);
        getMatches.setChecked(setting.isMatches());
        getMessages = findViewById(R.id.testing_settings_get_messages_switch);
        getMessages.setTag(Constants.GET_MESSAGES_ENDPOINT);
        getMessages.setOnCheckedChangeListener(this);
        getMessages.setChecked(setting.isMessages());
        sendMessage = findViewById(R.id.testing_settings_send_message_switch);
        sendMessage.setTag(Constants.SEND_MESSAGE_ENDPOINT);
        sendMessage.setOnCheckedChangeListener(this);
        sendMessage.setChecked(setting.isSendMessage());
        getSettings = findViewById(R.id.testing_settings_get_setting_switch);
        getSettings.setTag(Constants.GET_SETTINGS__ENDPOINT);
        getSettings.setOnCheckedChangeListener(this);
        getSettings.setChecked(setting.isGetSettings());
        saveSettings = findViewById(R.id.testing_settings_save_settings_switch);
        saveSettings.setTag(Constants.SAVE_SETTINGS_ENDPOINT);
        saveSettings.setOnCheckedChangeListener(this);
        saveSettings.setChecked(setting.isSetSettings());
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, isChecked ? "Enabled live server for " + buttonView.getTag() + " endpoint" : buttonView.getTag() + " endpoint is now mocked", Toast.LENGTH_SHORT).show();
        if (buttonView.equals(getProfiles)) {
            setting.setGetProfiles(isChecked);
        } else if (buttonView.equals(swiping)) {
            setting.setSwiping(isChecked);
        } else if (buttonView.equals(getMatches)) {
            setting.setMatches(isChecked);
        } else if (buttonView.equals(getMessages)) {
            setting.setMessages(isChecked);
        } else if (buttonView.equals(sendMessage)) {
            setting.setSendMessage(isChecked);
        } else if (buttonView.equals(getSettings)) {
            setting.setGetSettings(isChecked);
        } else if (buttonView.equals(saveSettings)) {
            setting.setSetSettings(isChecked);
        }
    }
}
