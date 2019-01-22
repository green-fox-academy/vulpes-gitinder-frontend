package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.reciever.AlarmSetUp;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch notificationSwitch, bSyncSwitch;
    SeekBar seekBar;
    TextView maximumDistance;
    ImageView imageView;

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Settings settings;
    @Inject
    AlarmSetUp alarmSetUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        notificationSwitch = (Switch) findViewById(R.id.notifications);
        bSyncSwitch = (Switch) findViewById(R.id.bckSync);
        notificationSwitch.setOnCheckedChangeListener(this);
        notificationSwitch.setTag(Constants.ENABLE_NOTIFICATIONS);
        bSyncSwitch.setOnCheckedChangeListener(this);
        bSyncSwitch.setTag(Constants.ENABLE_BACKGROUNDSYNC);
        notificationSwitch.setChecked(sharedPreferences.getBoolean((String) notificationSwitch.getTag(), false));
        bSyncSwitch.setChecked(sharedPreferences.getBoolean((String) bSyncSwitch.getTag(), false));
        settingSeekBar();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked() != sharedPreferences.getBoolean((String) buttonView.getTag(), false)) {
            settings.setEnableNotifications(isChecked);
            settings.setEnableBackgroundSync(isChecked);
            sharedPreferences.edit().putBoolean((String) buttonView.getTag(), isChecked).apply();
            Toast.makeText(SettingsActivity.this, isChecked ? "Enabled!" : "Diasbled!", Toast.LENGTH_SHORT).show();
            if (!bSyncSwitch.isChecked()) {
                alarmSetUp.stopAlarm();

            }
        }
    }

    public void settingSeekBar() {
        maximumDistance = (TextView) findViewById(R.id.maximumDistance);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        maximumDistance.setText(getString(R.string.settings_maximum_distance) + sharedPreferences.getInt(Constants.MAX_DISTANCE, 0));
        seekBar.setProgress(sharedPreferences.getInt(Constants.MAX_DISTANCE, 0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maximumDistance.setText(getString(R.string.settings_maximum_distance) + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                settings.setMaxDistance(seekBar.getProgress());
                sharedPreferences.edit().putInt(Constants.MAX_DISTANCE, seekBar.getProgress()).apply();
            }
        });
    }

    //Hardcoded image
    public void displayImage() {
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load("https://vignette.wikia.nocookie.net/rickandmorty/images/1/19/Pickle_rick_transparent.png/revision/latest?cb=20171025014216").into(imageView);
    }

}
