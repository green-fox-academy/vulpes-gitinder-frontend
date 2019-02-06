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
    public AlarmSetUp alarmSetUp;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public Settings settings;

    @Inject
    GitinderAPIService gitinderAPI;


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

        logoutButton.setOnClickListener(v -> {
            logout();
        });

        notificationSwitch.setOnCheckedChangeListener(this);
        notificationSwitch.setTag(Constants.ENABLE_NOTIFICATIONS);
        bSyncSwitch.setOnCheckedChangeListener(this);
        bSyncSwitch.setTag(Constants.ENABLE_BACKGROUNDSYNC);
        notificationSwitch.setChecked(sharedPreferences.getBoolean((String) notificationSwitch.getTag(), false));
        bSyncSwitch.setChecked(sharedPreferences.getBoolean((String) bSyncSwitch.getTag(), false));
        settingSeekBar();
        displayImage();
        alarmSetUp = new AlarmSetUp(view.getContext());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked() != sharedPreferences.getBoolean((String) buttonView.getTag(), false)) {
            settings.setEnableNotifications(isChecked);
            settings.setEnableBackgroundSync(isChecked);
            sharedPreferences.edit().putBoolean((String) buttonView.getTag(), isChecked).apply();
            Toast.makeText(getActivity().getApplicationContext(), isChecked ? "Enabled!" : "Diasbled!", Toast.LENGTH_SHORT).show();
            if (!bSyncSwitch.isChecked()) {
                alarmSetUp.stopAlarm(getContext());
            }else {
                alarmSetUp.startAlarm(getContext());
            }
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
        imageView = (ImageView) getView().findViewById(R.id.imageView);
        Picasso.get().load("https://short-biography.com/wp-content/uploads/tom-hanks/Thomas-Jeffrey-Hanks.jpg").into(imageView);
    }

    public void logout() {
        Call<GitinderResponse> call = gitinderAPI.provide(Constants.LOGOUT).logoutUser(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""));

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                sharedPreferences.edit().remove(Constants.GITINDER_TOKEN).apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
        Picasso.get().load("https://short-biography.com/wp-content/uploads/tom-hanks/Thomas-Jeffrey-Hanks.jpg").into(imageView);
    }

    @Override
    public void reload() {

        gitinderAPI.provide(Constants.GET_SETTINGS).getSettings(Constants.GITINDER_TOKEN).enqueue(new CustomCallback<Settings>() {

            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                notificationSwitch.setChecked(response.body().isEnableNotifications());
                bSyncSwitch.setChecked(response.body().isEnableBackgroundSync());
                maximumDistance.setText(Integer.toString(response.body().getMaxDistance()));
                seekBar.setProgress(response.body().getMaxDistance());
            }
        });
    }
}
