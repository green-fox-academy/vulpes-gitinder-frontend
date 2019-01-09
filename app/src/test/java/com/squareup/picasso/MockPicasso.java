package com.squareup.picasso;

import android.content.Context;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static com.squareup.picasso.TestUtils.NO_HANDLERS;

public class MockPicasso extends Picasso {
    private static String lastImagePath = null;
    private static ImageView lastTargetImageView = null;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Mock
    private static Context context;
    @Mock
    private static Dispatcher dispatcher;
    @Mock
    private static RequestTransformer transformer;
    @Mock
    private static Listener listener;

    public MockPicasso() {
        super(context, dispatcher, Cache.NONE, listener, transformer, NO_HANDLERS,
                new MockStats(), ARGB_8888, false, false);
    }

    public static void init() {
        singleton = new MockPicasso();
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
