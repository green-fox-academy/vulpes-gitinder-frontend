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

import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.UserSettings;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        notificationSwitch = (Switch) findViewById(R.id.notifications);
        bSyncSwitch = (Switch) findViewById(R.id.bckSync);
        notificationSwitch.setOnCheckedChangeListener(this);
        bSyncSwitch.setOnCheckedChangeListener(this);
        settingSeekBar();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.notifications:
                Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                settings.setEnableNotifications(isChecked);
                sharedPreferences.edit().putBoolean("enableNotifications", isChecked).apply();
                Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();

            case R.id.bckSync:
                Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                settings.setEnableBackgroundSync(isChecked);
                sharedPreferences.edit().putBoolean("enableBackgroundSync", isChecked).apply();
                Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();
        }
    }

    public void settingSeekBar() {
        maximumDistance = (TextView) findViewById(R.id.maximumDistance);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        maximumDistance.setText("Maximum Distance :" + seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maximumDistance.setText("Maximum Distance :" + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                settings.setMaxDistance(seekBar.getProgress());
                sharedPreferences.edit().putInt("maxDistance", seekBar.getProgress()).apply();
            }
        });
    }

    //Hardcoded image
    public void displayImage() {
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load("https://vignette.wikia.nocookie.net/rickandmorty/images/1/19/Pickle_rick_transparent.png/revision/latest?cb=20171025014216").into(imageView);
    }

}
