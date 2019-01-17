package com.greenfox.gitinder.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.ProfileActivity;
import com.greenfox.gitinder.model.BaseFragment;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.ProfileDiffCallback;
import com.greenfox.gitinder.model.factory.ProfileFactory;
import com.greenfox.gitinder.adapter.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.ArrayList;
import java.util.List;

public class SwipingFragment extends BaseFragment implements CardStackListener {
    private static final String TAG = "SwipingFragment";

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;
    private ProfileFactory profileFactory = new ProfileFactory();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiping_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupButton();
        setupCardStackView();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d(TAG, "onCardDragging: d = " + direction.name() + ", r = " + ratio);
    }

    @Override
    public void onCardSwiped(Direction direction) {
        Log.d(TAG, "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction);
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            paginate();
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
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
        });

        View rewind = getView().findViewById(R.id.rewind_button);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                        .setDirection(Direction.Bottom)
                        .setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .build();
                manager.setRewindAnimationSetting(setting);
                cardStackView.rewind();
            }
        });

        View like = getView().findViewById(R.id.like_button);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
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
        adapter = new CardStackAdapter(getActivity().getApplicationContext(), createProfiles());
        cardStackView = getView().findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private void paginate() {
        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = new ArrayList<Profile>() {{
            addAll(adapter.getProfiles());
            addAll(createProfiles());
        }};
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void reload() {
        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = createProfiles();
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void addFirst(final int size) {
        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = new ArrayList<Profile>() {{
            addAll(adapter.getProfiles());
            for (int i = 0; i < size; i++) {
                add(manager.getTopPosition(), ProfileFactory.createProfile());
            }
        }};
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void addLast(final int size) {
        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = new ArrayList<Profile>() {{
            addAll(adapter.getProfiles());
            for (int i = 0; i < size; i++) {
                add(ProfileFactory.createProfile());
            }
        }};
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void removeFirst(final int size) {
        if (adapter.getProfiles().isEmpty()) {
            return;
        }

        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = new ArrayList<Profile>() {{
            addAll(adapter.getProfiles());
            for (int i = 0; i < size; i++) {
                remove(manager.getTopPosition());
            }
        }};
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void removeLast(final int size) {
        if (adapter.getProfiles().isEmpty()) {
            return;
        }

        List<Profile> oldList = adapter.getProfiles();
        List<Profile> newList = new ArrayList<Profile>() {{
            addAll(adapter.getProfiles());
            for (int i = 0; i < size; i++) {
                remove(size() - 1);
            }
        }};
        ProfileDiffCallback callback = new ProfileDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setProfiles(newList);
        result.dispatchUpdatesTo(adapter);
    }

        private List<Profile> createProfiles() {
        List<Profile> profiles = new ArrayList<>();
            for (int i = 0; i < 10 ; i++) {
                ProfileFactory.createProfile();
        }
        return profiles;
    }
}
