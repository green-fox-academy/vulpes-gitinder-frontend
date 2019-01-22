package com.greenfox.gitinder.api.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.adapter.MatchAdapter;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BackgroundReceiver extends BroadcastReceiver {

    MatchAdapter matchAdapter;


    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GitinderAPI gitinderAPI;


    @Override
    public void onReceive(Context context, Intent intent) {
            Call<Matches> matchesCall = gitinderAPI.matches(sharedPreferences.getString(Constants.GITINDER_TOKEN, "abc"));

            matchesCall.enqueue(new Callback<Matches>() {
                @Override
                public void onResponse(Call<Matches> call, Response<Matches> response) {
                    List<Match> matchList = response.body().getMatches();
                    matchAdapter.addMatches(matchList);
                }

                @Override
                public void onFailure(Call<Matches> call, Throwable t) {

                }
            });
    }
}
