package com.greenfox.gitinder.api.service;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;

public class MatchService {

    SharedPreferences sharedPreferences;

    public MatchService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void editNewMatchesCount(String count){
        sharedPreferences.edit().putString(Constants.MATCHES_COUNT, count).apply();
    }

    public void removeNewMatchesCount(){
        sharedPreferences.edit().remove(Constants.MATCHES_COUNT).apply();
    }

    public String getNewMatchesCount(){
        return sharedPreferences.getString(Constants.MATCHES_COUNT, "");
    }

    public boolean containsNewMatchesCount(){
        return sharedPreferences.contains(Constants.MATCHES_COUNT);
    }

    public void hideFloatingButtonWhenNoNewMatches(FloatingActionButton button, TextView buttonText){
        if(getNewMatchesCount().equals("0") || getNewMatchesCount().equals("") || !containsNewMatchesCount()){
            button.hide();
            buttonText.setText("");
        } else {
            button.show();
            buttonText.setText(getNewMatchesCount());
        }
    }

}
