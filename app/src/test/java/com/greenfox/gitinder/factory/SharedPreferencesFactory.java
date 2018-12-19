package com.greenfox.gitinder.factory;

import android.content.Context;
import android.content.SharedPreferences;

import com.greenfox.gitinder.Constants;

import androidx.test.core.app.ApplicationProvider;

public class SharedPreferencesFactory {

    public static SharedPreferences getSharedPref() {
        SharedPreferences sharedPreferences = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}
