package com.greenfox.gitinder.adapter;

import android.support.v7.widget.RecyclerView;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

@Implements(CardStackLayoutManager.class)
public class ShadowCardStackLayoutManager {

    @RealObject
    CardStackLayoutManager cardStackLayoutManager;

    @Implementation
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State s) {
    }
}
