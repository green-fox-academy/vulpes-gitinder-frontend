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
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.CardStackAdapter;
import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.BaseFragment;
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
        setupButton();
        setupCardStackView();
        loadProfiles();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d(TAG, "onCardDragging: d = " + direction.name() + ", r = " + ratio + " + 3 velké hrušky");
    }

    @Override
    public void onCardSwiped(Direction direction) {
        Log.d(TAG, "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction + "+ tři oříšky pro Popelku");
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            loadProfiles();
        }
    }

    @Override
    public void onCardRewound() {
        Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
    }

    @Override
    public void onCardCanceled() {
        Log.d(TAG, "onCardCanceled:" + manager.getTopPosition());
    }

    @Override
    public void onCardAppeared(View view, int position) {
        try{
            TextView textView = view.findViewById(R.id.item_name);
            Log.d(TAG, "onCardAppeared: (" + position + ") " + textView.getText());
        } catch(Exception e){
            Log.d(TAG, "onCardAppeared: item_name is null");
        }
    }

    @Override
    public void onCardDisappeared(View view, int position) {
        try{
            TextView textView = view.findViewById(R.id.item_name);
            Log.d(TAG, "onCardDisappeared: (" + position + ") " + textView.getText());
        } catch(Exception e){
            Log.d(TAG, "onCardDisappeared: item_name is null");
        }
    }

    private void setupCardStackView() {
        initialize();
    }

    private void setupButton() {
        View skip = getView().findViewById(R.id.skip_button);
        skip.setOnClickListener(v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(200)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();
            manager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
        });

        View rewind = getView().findViewById(R.id.rewind_button);
        rewind.setOnClickListener(v -> {
            RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                    .setDirection(Direction.Bottom)
                    .setDuration(200)
                    .setInterpolator(new DecelerateInterpolator())
                    .build();
            manager.setRewindAnimationSetting(setting);
            cardStackView.rewind();
        });

        View like = getView().findViewById(R.id.like_button);
        like.setOnClickListener(v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(200)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();
            manager.setSwipeAnimationSetting(setting);
            cardStackView.swipe();
        });
    }

    private void initialize() {
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
}
