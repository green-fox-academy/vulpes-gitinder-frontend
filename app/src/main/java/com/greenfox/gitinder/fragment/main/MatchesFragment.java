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
import android.widget.Button;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MatchAdapter;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MatchesFragment extends BaseFragment implements MatchService.MatchesListener {
    private static final String TAG = "MatchesFragment";

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    MatchService matchService;

    MatchAdapter matchAdapter;

    public Button addMatchesButton;
    Button clearMatchesButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_matches_recycler_view);
        addMatchesButton = getView().findViewById(R.id.add_matches_button);
        clearMatchesButton = getView().findViewById(R.id.clear_matches_button);
        matchService.setMatchesListener(this);

        if(!BuildConfig.FLAVOR.equals("dev")){
            addMatchesButton.setVisibility(View.GONE);
            clearMatchesButton.setVisibility(View.GONE);
        }

        matchAdapter = new MatchAdapter(getActivity().getApplicationContext(), matchService);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(matchAdapter);

        addMatchesButton.setOnClickListener(v -> updateMatches());
        clearMatchesButton.setOnClickListener(v -> matchService.clearMatches());
    }

    public void updateMatches(){
        Call<Matches> call = gitinderAPI.matches(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""));

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d(TAG, "Getting matches - SUCCESS");

                List<Match> responseMatches = response.body().getMatches();

                matchService.addMatches(responseMatches);
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.d(TAG, "Getting matches - FAILURE");
            }
        });
    }

    @Override
    public void onMatchesChanged(List<Match> updatedMatches) {
        matchAdapter.clearMatches();
        matchAdapter.addMatches(updatedMatches);
        matchAdapter.notifyDataSetChanged();
    }
}
