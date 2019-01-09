package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.util.List;

public class MockPicasso extends Picasso {
    private static String lastImagePath = null;
    private static ImageView lastTargetImageView = null;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Mock
    Context context;
    @Mock
    Dispatcher dispatcher;
    @Mock
    RequestTransformer transformer;
    @Mock
    Listener listener;

    MockPicasso(Context context, Dispatcher dispatcher, Cache cache, Listener listener, RequestTransformer requestTransformer, List<RequestHandler> extraRequestHandlers, Stats stats, Bitmap.Config defaultBitmapConfig, boolean indicatorsEnabled, boolean loggingEnabled) {
        super(context, dispatcher, cache, listener, requestTransformer, extraRequestHandlers, stats, defaultBitmapConfig, indicatorsEnabled, loggingEnabled);
    }


    public static void init() {
        singleton = TestUtils.mockPicasso();
    }

    public static String getLastImagePath() {
        return lastImagePath;
    }

    public static ImageView getLastTargetImageView() {
        return lastTargetImageView;
    }

    @Override
    public RequestCreator load(String path) {
        lastImagePath = path;
        return new MockRequestCreator();
    }

    class MockRequestCreator extends RequestCreator {
        @Override
        public void into(ImageView target) {
            lastTargetImageView = target;
        }
    }

    static class MockStats extends Stats {
        MockStats() {
            super(Cache.NONE);
        }
    }
}
