package com.greenfox.gitinder.fragment.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MatchAdapter;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.service.NotificationService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchesFragment extends BaseFragment {
    private static final String TAG = "MatchesFragment";

    @Inject
    GitinderAPIService gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    MatchService matchService;

    MatchAdapter matchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        matchService.updateMatches();
    }

    public void setupRecyclerViewAndAdapter(){
        RecyclerView recyclerView = getView().findViewById(R.id.fragment_matches_recycler_view);

        matchAdapter = new MatchAdapter(getActivity(), matchService, gitinderAPI, sharedPreferences);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(matchAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupRecyclerViewAndAdapter();
    }

    public void updateMatches(){
        Call<Matches> call = gitinderAPI.provide(Constants.GET_MATCHES).matches(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""));

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d(TAG, "onResponse: matches called");
                if (response.body() != null && sharedPreferences.getBoolean(Constants.ENABLE_NOTIFICATIONS,false)){
                    List<Match> newMatchList = response.body().getMatches();
                    Log.d(TAG, "onResponse: received new matchList" + (newMatchList.size() - matchService.getMatchList().size()));
                    if (matchService.getMatchList().size() == 0) {
                        matchService.addMatches(response.body().getMatches());
                        for (Match m: response.body().getMatches()) {
                        }
                    } else {
                        for (int i = 0; i < newMatchList.size(); i++) {
                            if (!matchService.getMatchList().contains(newMatchList.get(i))) {
                                matchService.addMatch(newMatchList.get(i));
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.d(TAG, "Getting matches - FAILURE");
            }
        });
    }

    @Override
    public void reload() {
        updateMatches();
    }
}
