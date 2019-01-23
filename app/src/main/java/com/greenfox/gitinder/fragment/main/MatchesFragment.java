package com.greenfox.gitinder.fragment.main;

import android.content.Context;
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
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MatchAdapter;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.model.factory.MatchFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends BaseFragment {
    private static final String TAG = "MatchesFragment";

    MatchAdapter matchAdapter;

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

    Button matchesUpdaterButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_matches_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        matchesUpdaterButton = view.findViewById(R.id.updaterButtonDude);
        matchAdapter = new MatchAdapter(getActivity());
        loadMatches();
        recyclerView.setAdapter(matchAdapter);

        matchesUpdaterButton.setOnClickListener(v -> {
            addMatch();
            Toast.makeText(getActivity(), "Match added.", Toast.LENGTH_SHORT).show();
        });
    }

    public void loadMatches(){
        Call<Matches> call = gitinderAPI.matches(sharedPreferences.getString(Constants.GITINDER_TOKEN, "aaa"));

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d(TAG, "Getting matches - SUCCESS");

                List<Match> matchList = response.body().getMatches();

                matchAdapter.addMatches(matchList);
                sharedPreferences.edit().putString(Constants.MATCHES_COUNT, toStringGoddammit(matchAdapter.getItemCount())).apply();
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.d(TAG, "Getting matches - FAILURE");
            }
        });
    }

    public void addMatch(){
        List<Match> matchList2 = new ArrayList<>();
        matchList2.add(MatchFactory.createNewMatch());
        matchAdapter.addMatches(matchList2);
        sharedPreferences.edit().putString(Constants.MATCHES_COUNT, toStringGoddammit(matchAdapter.getItemCount())).apply();
    }

    public String toStringGoddammit(Integer number){
        return number.toString();
    }
}
