package com.greenfox.gitinder.api.reciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.Constants;

import javax.inject.Inject;

public class AlarmSetUp {
    private Context alarmContext;
    private AlarmManager alarm;
    private PendingIntent pendingIntent;

    @Inject
    SharedPreferences sharedPreferences;

//    public AlarmSetUp() {
//    }

    public AlarmSetUp(Context context) {
        alarmContext = context;
        alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void startAlarm() {
        if (sharedPreferences.getBoolean(Constants.ENABLE_BACKGROUNDSYNC, true)) {
            Intent intent = new Intent(alarmContext, BackgroundReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(alarmContext, 0, intent, PendingIntent.FLAG_NO_CREATE);
            alarm.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 300000, pendingIntent);

        }
    }

    public void stopAlarm() {
        alarm.cancel(pendingIntent);
    }
}
