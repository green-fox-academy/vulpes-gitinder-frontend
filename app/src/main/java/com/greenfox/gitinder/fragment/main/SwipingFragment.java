package com.greenfox.gitinder.fragment.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.CardStackAdapter;
import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.api.model.SwipeResponse;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.factory.MatchFactory;
import com.greenfox.gitinder.service.NotificationService;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwipingFragment extends BaseFragment implements CardStackListener {
    private static final String TAG = "SwipingFragment";

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;

    TextView extinctText;

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    MatchService matchService;

    @Inject
    NotificationService notificationService;

    Button likeButton;
    Button nopeButton;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiping_fragment, container, false);
        return view;
    }
 
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        extinctText = getView().findViewById(R.id.swiping_fragment_extinct);
        likeButton = getView().findViewById(R.id.like_button);
        nopeButton =  getView().findViewById(R.id.skip_button);
        handler = new Handler();
        setupButtons();
        setupCardStackView();
        loadProfiles();
    }

    @Override
    public void onCardSwiped(Direction direction) {
        disableButtonsAndSwiping();

        handler.postDelayed(() -> {
            if (manager.getItemCount() == 1){
                extinctText.setText("The cards have gone extinct.");
            } else {
                Log.d(TAG, "onCardSwiped: 1/2 manager.getTopPosition(): " + manager.getTopPosition());
                Log.d(TAG, "onCardSwiped: 1/2 manager.getItemCount(): " + manager.getItemCount());
                if (manager.getItemCount() > 1){
                    profileDirection(manager.getTopPosition() - 1, direction);
                } else {
                    profileDirection(0, direction);
                }
                Log.d(TAG, "onCardSwiped: 2/2 manager.getTopPosition(): " + manager.getTopPosition());
                Log.d(TAG, "onCardSwiped: 2/2 manager.getItemCount(): " + manager.getItemCount());
                extinctText.setText("");
            }
            enableButtonsAndSwiping();
        }, 200);
    }

    private void setupButtons(){
        setupButtonSwipe(likeButton, Direction.Right);
        setupButtonSwipe(nopeButton, Direction.Left);
        Log.d(TAG, "setupButtons: " + likeButton.isClickable());
        Log.d(TAG, "setupButtons: " + nopeButton.isClickable());
    }

    private void setupButtonSwipe(View view, Direction direction){
        view.setOnClickListener(v -> {
            disableButtonsAndSwiping();
            Log.d(TAG, "setupButtonSwipe: Like/Nope clicked");
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(direction)
                    .setDuration(160)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();
            manager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
            Log.d(TAG, "setupButtonSwipe: Swiped " + direction);
        });
    }

    private void setupCardStackView() {
        manager = new CardStackLayoutManager(getActivity(), this);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(false);
        adapter = new CardStackAdapter(getActivity());
        cardStackView = getView().findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private void loadProfiles() {
        Call<AvailableProfiles> call = gitinderAPI.getAvailable(sharedPreferences.getString(Constants.GITINDER_TOKEN, "aaa"));

        call.enqueue(new Callback<AvailableProfiles>() {
            @Override
            public void onResponse(Call<AvailableProfiles> call, Response<AvailableProfiles> response) {
                Log.d(TAG, "Getting available profiles - SUCCESS");
                List<Profile> profiles = response.body().getProfiles();
                adapter.addProfiles(profiles);
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<AvailableProfiles> call, Throwable t) {
                showProgressBar();
                Log.d(TAG, "Getting available profiles - FAILURE");
            }
        });
    }

    private void seenProfile(int position){
        Call<GitinderResponse> call = gitinderAPI.seenProfile(sharedPreferences.getString(Constants.GITINDER_TOKEN,"aaa"),
                adapter.getProfiles().get(position).getUsername());

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                Log.d(TAG, "Profile seen - SUCCESS");
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {
                Log.d(TAG, "Profile seen - FAILURE");

            }
        });
    }

    private void profileDirection(int position,Direction direction){
        if (manager.getItemCount() > 1){
            adapter.deleteProfile(adapter.getProfiles().get(position));
        }

        Call<SwipeResponse> call = gitinderAPI.swipe(sharedPreferences.getString(Constants.GITINDER_TOKEN,"aaa"),
                adapter.getProfiles().get(position).getUsername(),direction.toString().toLowerCase());

        call.enqueue(new Callback<SwipeResponse>() {
            @Override
            public void onResponse(Call<SwipeResponse> call, Response<SwipeResponse> response) {
                Log.d(TAG, "Profile direction added - SUCCESS");
                if (!(response.body().getMatch() == null)){
                    matchService.addMatch(response.body().getMatch());
                    notificationService.createNotificationChannel(getActivity());
                    notificationService.pushNewMatchNotification(response.body().getMatch(), getActivity());
                }
            }

            @Override
            public void onFailure(Call<SwipeResponse> call, Throwable t) {
                Log.d(TAG, "Profile direction added - FAILURE");
            }
        });
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
    }

    @Override
    public void onCardRewound() {
    }

    @Override
    public void onCardCanceled() {
    }

    @Override
    public void onCardAppeared(View view, int position) {
        Log.d(TAG, "onCardAppeared: adapter.getProfiles().size() " + adapter.getProfiles().size());
        if (position > 1){
            seenProfile(position);
        }
    }

    @Override
    public void onCardDisappeared(View view, int position) {
    }

    @Override
    public void reload() {
        if (adapter.getItemCount() == 0) {
            showProgressBar();
            loadProfiles();
        } else {
            loadProfiles();
        }
        cardStackView.refreshDrawableState();
        enableButtonsAndSwiping();
    }

    public void disableButtonsAndSwiping(){
        cardStackView.setClickable(false);
        likeButton.setClickable(false);
        nopeButton.setClickable(false);
    }

    public void enableButtonsAndSwiping(){
        cardStackView.setClickable(true);
        likeButton.setClickable(true);
        nopeButton.setClickable(true);
    }

}
