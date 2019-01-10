package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockPicasso extends Picasso {

    MockPicasso(Context context, Dispatcher dispatcher, Cache cache, Listener listener, RequestTransformer requestTransformer, List<RequestHandler> extraRequestHandlers, Stats stats, Bitmap.Config defaultBitmapConfig, boolean indicatorsEnabled, boolean loggingEnabled) {
        super(context, dispatcher, cache, listener, requestTransformer, extraRequestHandlers, stats, defaultBitmapConfig, indicatorsEnabled, loggingEnabled);
    }

    public static void init() {
        singleton = mock(Picasso.class);
        when(singleton.load(anyString())).thenReturn(new MockRequestCreator());
    }

    static class MockRequestCreator extends RequestCreator {
        @Override
        public void into(ImageView target) {}
    }
}
