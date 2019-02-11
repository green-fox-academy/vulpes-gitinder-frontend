package com.greenfox.gitinder.fragment.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.MainActivity;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.api.reciever.AlarmSetUp;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Settings;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "SettingsFragment";

    public Switch notificationSwitch, bSyncSwitch;
    public SeekBar seekBar;
    public TextView maximumDistance;
    public ImageView imageView;
    public Button logoutButton;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Settings settings;

    @Inject
    GitinderAPIService gitinderAPI;

    @Inject
    AlarmSetUp alarmSetUp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        notificationSwitch = getView().findViewById(R.id.notifications);
        bSyncSwitch = getView().findViewById(R.id.bckSync);
        logoutButton = getView().findViewById(R.id.settings_logout_button);
        settingSeekBar();
        displayImage();
        reload();

        logoutButton.setOnClickListener(v -> {
            logout();
        });

        notificationSwitch.setOnCheckedChangeListener(this);
        notificationSwitch.setTag(Constants.ENABLE_NOTIFICATIONS);
        bSyncSwitch.setOnCheckedChangeListener(this);
        bSyncSwitch.setTag(Constants.ENABLE_BACKGROUNDSYNC);
        notificationSwitch.setChecked(sharedPreferences.getBoolean((String) notificationSwitch.getTag(), false));
        bSyncSwitch.setChecked(sharedPreferences.getBoolean((String) bSyncSwitch.getTag(), false));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked() != sharedPreferences.getBoolean((String) buttonView.getTag(), false)) {
            
          if (!bSyncSwitch.isChecked()) {
                alarmSetUp.stopAlarm();
            }else {
                alarmSetUp.startAlarm();
            }
            sharedPreferences.edit().putBoolean((String) buttonView.getTag(), isChecked).apply();

            Call<GitinderResponse> gitinderResponseCall = gitinderAPI.provide(Constants.SAVE_SETTINGS)
                    .updateSettings(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""), getSharedPrefSettings());
            gitinderResponseCall.enqueue(new CustomCallback<GitinderResponse>() {
                @Override
                public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                    Log.d(TAG, "onCheckedChanged: onResponse: SUCCESSFUL");

                    Log.d(TAG, "onResponse SAVE: Code: " + response.code());
                    Log.d(TAG, "onResponse SAVE: Message: " + response.message());
                    Log.d(TAG, "onResponse SAVE: Body: " + response.body());

                    Toast.makeText(getActivity().getApplicationContext(), isChecked ? "Enabled!" : "Diasbled!", Toast.LENGTH_SHORT).show();
                    if (!bSyncSwitch.isChecked()) {
                        alarmSetUp.stopAlarm();
                    }else {
                        alarmSetUp.startAlarm();
                    }
                }
            });

        }
    }

    public void settingSeekBar() {
        maximumDistance = (TextView) getView().findViewById(R.id.maximumDistance);
        seekBar = (SeekBar) getView().findViewById(R.id.seekBar);
        maximumDistance.setText(getString(R.string.settings_maximum_distance) + sharedPreferences.getInt(Constants.MAX_DISTANCE, 0));
        seekBar.setProgress(sharedPreferences.getInt(Constants.MAX_DISTANCE, 0));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maximumDistance.setText(getString(R.string.settings_maximum_distance) + " " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                settings.setMaxDistance(seekBar.getProgress());
                sharedPreferences.edit().putInt(Constants.MAX_DISTANCE, seekBar.getProgress()).apply();

                Call<GitinderResponse> gitinderResponseCall = gitinderAPI.provide(Constants.SAVE_SETTINGS)
                        .updateSettings(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""), getSharedPrefSettings());
                gitinderResponseCall.enqueue(new CustomCallback<GitinderResponse>() {
                    @Override
                    public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                        Log.d(TAG, "onStopTrackingTouch: onResponse: SUCCESSFUL");

                        Log.d(TAG, "onResponse SAVE: Code: " + response.code());
                        Log.d(TAG, "onResponse SAVE: Message: " + response.message());
                        Log.d(TAG, "onResponse SAVE: Body: " + response.body());
                    }
                });
            }
        });
    }

    //Hardcoded image
    public void displayImage() {
        imageView = (ImageView) getView().findViewById(R.id.imageView);
        Picasso.get().load(sharedPreferences.getString(Constants.USER_PICTURE, "")).into(imageView);
    }

    public Settings getSharedPrefSettings(){
        Settings putSettings = new Settings();
        putSettings.setEnableBackgroundSync(sharedPreferences.getBoolean(Constants.ENABLE_BACKGROUNDSYNC, false));
        putSettings.setEnableNotifications(sharedPreferences.getBoolean(Constants.ENABLE_NOTIFICATIONS, false));
        putSettings.setMaxDistance(sharedPreferences.getInt(Constants.MAX_DISTANCE, 0));
        putSettings.setPreferredLanguages(new ArrayList<>());
        return  putSettings;
    }

    public void logout() {
        Call<GitinderResponse> call = gitinderAPI.provide(Constants.LOGOUT).logoutUser(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""));

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    @Override
    public void reload() {

        gitinderAPI.provide(Constants.GET_SETTINGS).getSettings(sharedPreferences.getString(Constants.GITINDER_TOKEN, "aaa")).enqueue(new CustomCallback<Settings>() {

            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                Log.d(TAG, "onResponse GET: Code: " + response.code());
                Log.d(TAG, "onResponse GET: Message: " + response.message());
                Log.d(TAG, "onResponse GET: Body: " + response.body());
                notificationSwitch.setChecked(response.body().isEnableNotifications());
                bSyncSwitch.setChecked(response.body().isEnableBackgroundSync());
                maximumDistance.setText(getString(R.string.settings_maximum_distance) + " " + Integer.toString(response.body().getMaxDistance()));
                seekBar.setProgress(response.body().getMaxDistance());
            }
        });
    }
}
