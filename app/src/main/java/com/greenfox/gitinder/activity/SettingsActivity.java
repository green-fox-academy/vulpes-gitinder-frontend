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
        notificationSwitch.setChecked(sharedPreferences.getBoolean("enableNotifications", false));
        bSyncSwitch.setChecked(sharedPreferences.getBoolean("enableBackgroundSync", false));
        settingSeekBar();
        displayImage();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.notifications:
                if (notificationSwitch.isChecked() && !sharedPreferences.getBoolean("enableNotifications", false)) {
                    settings.setEnableNotifications(isChecked);
                    sharedPreferences.edit().putBoolean("enableNotifications", isChecked).apply();
                    Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                } else if (!notificationSwitch.isChecked() && sharedPreferences.getBoolean("enableNotifications", false)) {
                    settings.setEnableNotifications(isChecked);
                    sharedPreferences.edit().putBoolean("enableNotifications", isChecked).apply();
                    Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bckSync:
                if (bSyncSwitch.isChecked() && !sharedPreferences.getBoolean("enableBackgroundSync", false)) {
                    settings.setEnableBackgroundSync(isChecked);
                    sharedPreferences.edit().putBoolean("enableBackgroundSync", isChecked).apply();
                    Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                } else if (!bSyncSwitch.isChecked() && sharedPreferences.getBoolean("enableBackgroundSync", false)) {
                    settings.setEnableBackgroundSync(isChecked);
                    sharedPreferences.edit().putBoolean("enableBackgroundSync", isChecked).apply();
                    Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void settingSeekBar() {
        maximumDistance = (TextView) findViewById(R.id.maximumDistance);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        maximumDistance.setText("Maximum distance :" + sharedPreferences.getInt("maxDistance", 0));
        seekBar.setProgress(sharedPreferences.getInt("maxDistance", 0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maximumDistance.setText("Maximum distance :" + seekBar.getProgress());
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
