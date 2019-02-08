package com.greenfox.gitinder.api.reciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;

import javax.inject.Inject;

public class AlarmSetUp {
    private Context alarmContext;
    private AlarmManager alarm;
    private PendingIntent pendingIntent;


    public AlarmSetUp(Context context) {
        alarmContext = context;
    }

    public AlarmSetUp() {
    }

    public void setAlarmContext(Context alarmContext) {
        this.alarmContext = alarmContext;
    }

    public void startAlarm() {
        alarm = (AlarmManager) alarmContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(alarmContext, BackgroundReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(alarmContext, 0, intent, 0);
        if (BuildConfig.FLAVOR.equals(Constants.STAGING)) {
            alarm.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 10000, pendingIntent);
        }
        alarm.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 60000, pendingIntent);
    }

    public void stopAlarm() {
        alarm = (AlarmManager) alarmContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(alarmContext, BackgroundReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(alarmContext, 0, intent, 0);
        alarm.cancel(pendingIntent);
    }
}
