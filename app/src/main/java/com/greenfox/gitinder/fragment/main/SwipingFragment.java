package com.greenfox.gitinder.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.fragment.BaseFragment;
import com.squareup.picasso.Picasso;
import com.greenfox.gitinder.adapter.CardStackAdapter;
import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.Profile;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
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

    @Inject
    GitinderAPI gitinderAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiping_fragment, container, false);
        Log.d(TAG, "onCreateView: asd");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //AndroidInjection.inject(getActivity());
        Log.d(TAG, "onAttach: asd");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: asdf");
        setupButtons();
        setupCardStackView();
        loadProfiles();
    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            loadProfiles();
        }
    }

    private void setupButtons(){
        setupButtonSwipe(getView().findViewById(R.id.like_button), Direction.Right);
        setupButtonSwipe(getView().findViewById(R.id.skip_button), Direction.Left);
        setupButtonRewind(getView().findViewById(R.id.rewind_button), Direction.Bottom);
    }

    private void setupButtonSwipe(View view, Direction direction){
        view.setOnClickListener(v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(direction)
                    .setDuration(200)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();
            manager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
        });
    }

    private void setupButtonRewind(View view, Direction direction){
        view.setOnClickListener(v -> {
            RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                    .setDirection(direction)
                    .setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            manager.setRewindAnimationSetting(setting);
            cardStackView.rewind();
        });
    }

    private void setupCardStackView() {
        manager = new CardStackLayoutManager(getActivity().getApplicationContext(), this);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(getActivity().getApplicationContext());
        cardStackView = getView().findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private void loadProfiles() {
        Call<AvailableProfiles> call = gitinderAPI.getAvailable(Constants.GITINDER_TOKEN);

        call.enqueue(new Callback<AvailableProfiles>() {
            @Override
            public void onResponse(Call<AvailableProfiles> call, Response<AvailableProfiles> response) {
                Log.d(TAG, "Getting available profiles - SUCCESS");
                List<Profile> profiles = response.body().getProfiles();

                adapter.addProfiles(profiles);
            }

            @Override
            public void onFailure(Call<AvailableProfiles> call, Throwable t) {
                Log.d(TAG, "Getting available profiles - FAILURE");
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
    }

    @Override
    public void onCardDisappeared(View view, int position) {
    }
}
