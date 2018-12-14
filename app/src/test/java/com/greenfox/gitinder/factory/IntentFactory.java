package com.greenfox.gitinder.factory;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

import androidx.test.core.app.ApplicationProvider;

import static org.robolectric.Shadows.shadowOf;

public class IntentFactory {

    public static Intent getNextStartedActivity() {
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        return actual;
    }

    public static Intent getGitHubCallBackIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("gitinder://githubcallback?code=7fd23c00de517e3b78c2"));
        intent.setFlags(339738624);
        intent.setComponent(ComponentName.createRelative("com.greenfox.gitinder", ".activities.Login"));
        return intent;
    }
}
