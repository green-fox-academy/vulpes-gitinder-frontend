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

import com.greenfox.gitinder.Model.UserSettings;
import com.greenfox.gitinder.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SettingsActivity extends AppCompatActivity {

    Switch aSwitch, bSwitch;
    SeekBar seekBar;
    TextView maximumDistance;
    ImageView imageView;

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    UserSettings userSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsSwitchButtons();
        settingSeekBar();
        displayImage();


    }

    //Hardcoded image
    public void displayImage() {
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load("https://vignette.wikia.nocookie.net/rickandmorty/images/1/19/Pickle_rick_transparent.png/revision/latest?cb=20171025014216").into(imageView);
    }

    public void settingsSwitchButtons() {
        aSwitch = (Switch) findViewById(R.id.notifications);
        bSwitch = (Switch) findViewById(R.id.bckSync);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                    userSettings.setEnableNotification(true);
                    sharedPreferences.edit().putBoolean("enableNotifications", true).apply();
                } else {
                    Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();
                    userSettings.setEnableNotification(false);
                    sharedPreferences.edit().putBoolean("enableNotifications", false).apply();
                }
            }
        });
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this, "Enabled!", Toast.LENGTH_SHORT).show();
                    userSettings.setEnableBackgroundSync(true);
                    sharedPreferences.edit().putBoolean("enableBackgroundSync", true).apply();
                } else {
                    Toast.makeText(SettingsActivity.this, "Disabled!", Toast.LENGTH_SHORT).show();
                    userSettings.setEnableBackgroundSync(false);
                    sharedPreferences.edit().putBoolean("enableBackgroundSync", false).apply();

                }
            }
        });

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
                userSettings.setMaxDistance(seekBar.getProgress());
                sharedPreferences.edit().putInt("maximumDistance",seekBar.getProgress()).apply();
            }
        });
    }
}
