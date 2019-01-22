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
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.MatchAdapter;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends BaseFragment {
    private static final String TAG = "MatchesFragment";

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

    MatchAdapter matchAdapter;
    TextView floatingActionButtonText;
    Button addMatchesButton;

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
        floatingActionButtonText = getActivity().findViewById(R.id.floating_action_button_text);
        addMatchesButton = getView().findViewById(R.id.add_matches_button);

        matchAdapter = new MatchAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addMatchesButton.setOnClickListener(v -> {
            loadMatches();
        });

        loadMatches();
        recyclerView.setAdapter(matchAdapter);


        floatingActionButtonText.setText(sharedPreferences.getString(Constants.MATCHES_COUNT, ""));

        sharedPreferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            if (key.equals(Constants.MATCHES_COUNT)){
                floatingActionButtonText.setText(sharedPreferences.getString(Constants.MATCHES_COUNT, ""));
            }
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
                matchAdapter.notifyDataSetChanged();
                Log.d(TAG, "matchAdapter.getItemCount: " + matchAdapter.getItemCount());
                sharedPreferences.edit().putString(Constants.MATCHES_COUNT, String.valueOf(matchAdapter.getItemCount()));
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.d(TAG, "Getting matches - FAILURE");
            }
        });
    }
}
