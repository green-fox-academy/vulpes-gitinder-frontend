package com.greenfox.gitinder.model;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;

public class FloatingButtonHider {

    public static void hideFloatingButtonWhenNoNewMatches(SharedPreferences pref, FloatingActionButton button, TextView buttonText){
        if(pref.getString(Constants.MATCHES_COUNT, "").equals("0") ||
           pref.getString(Constants.MATCHES_COUNT, "").equals("") ||
          !pref.contains(Constants.MATCHES_COUNT)){
            button.hide();
            buttonText.setText("");
        } else {
            button.show();
            buttonText.setText(pref.getString(Constants.MATCHES_COUNT, ""));
        }
    }
}
