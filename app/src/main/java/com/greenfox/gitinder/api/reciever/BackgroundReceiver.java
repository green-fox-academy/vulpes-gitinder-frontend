package com.greenfox.gitinder.api.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.greenfox.gitinder.Constants;

import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.service.NotificationService;


import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BackgroundReceiver extends BroadcastReceiver {
    private static final String TAG = "BackgroundReceiver";


    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GitinderAPIService gitinderAPI;

    @Inject
    MatchService matchService;

    @Inject
    NotificationService notificationService;


    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this,context);
        Call<Matches> matchesCall = gitinderAPI.provide(Constants.GET_MATCHES).matches(sharedPreferences.getString(Constants.GITINDER_TOKEN, "abc"));
        matchesCall.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d(TAG, "onResponse: matches called");
                if (response.body() != null && sharedPreferences.getBoolean(Constants.ENABLE_NOTIFICATIONS,false)){
                    List<Match> newMatchList = response.body().getMatches();
                    Log.d(TAG, "onResponse: received new matchList" + (newMatchList.size() - matchService.getMatchList().size()));
                    if (matchService.getMatchList().size() == 0) {
                        matchService.addMatches(response.body().getMatches());
                        for (Match m: response.body().getMatches()) {
                            notificationService.pushNewMatchNotification(m, context);
                        }
                    } else {
                        for (int i = 0; i < newMatchList.size(); i++) {
                                if (!matchService.getMatchList().contains(newMatchList.get(i))) {
                                    matchService.addMatch(newMatchList.get(i));
                                    notificationService.pushNewMatchNotification(newMatchList.get(i), context);
                                    Log.d(TAG, "onResponse: notification fired");
                                }
                            }
                        }
                    }

            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {

            }
        });
    }
}
