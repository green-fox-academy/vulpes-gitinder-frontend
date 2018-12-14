package com.greenfox.gitinder.activities;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.webkit.WebView;

import com.greenfox.gitinder.Clients.GitHubClient;
import com.greenfox.gitinder.Clients.GitHubClientMock;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.Model.GitHubToken;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import androidx.test.core.app.ApplicationProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    Login login;

    @Test
    public void buttonTextTest() {
        login = Robolectric.setupActivity(Login.class);
        assertEquals("Login with Github", login.login.getText());
    }
    @Test
    public void testRedirectTrue() {
        SharedPreferences preferences =  ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GITINDER_TOKEN, "aaa").apply();
        login = Robolectric.setupActivity(Login.class);
        Intent expectedIntent = new Intent(login, MainActivity.class);
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
    @Test
    public void testRedirectFail() {
        SharedPreferences preferences = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ryba", "aaa").apply();
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(null, actual);
    }
    @Test
    public void testButtonWillRedirect() {
        login = Robolectric.setupActivity(Login.class);
        login.login.performClick();
        Intent expectedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="+Constants.GITHUB_CLIENT_ID+"&redirect_uri="+Constants.GITHUB_CALLBACK));
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
    @Test
    public void testLoggingWillGetTokenBack() {
        GitHubClientMock clientMock = new GitHubClientMock();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("gitinder://githubcallback?code=7fd23c00de517e3b78c2"));
        intent.setFlags(339738624);
        intent.setComponent(ComponentName.createRelative("com.greenfox.gitinder", ".activities.Login"));
        ActivityController<Login> controller = Robolectric.buildActivity(Login.class, intent).create().start();
        Activity login = controller.get();
        controller.resume();
        String code = login.getIntent().getData().getQueryParameter("code");
        Call<GitHubToken> call = clientMock.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code);
        call.enqueue(new Callback<GitHubToken>() {
            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                assertEquals("aaa", response.body().getToken());
            }

            @Override
            public void onFailure(Call<GitHubToken> call, Throwable t) {

            }
        });
    }
    @Test
    public void tokenIsSaving() {
        SharedPreferences sharedPreferences = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        GitHubClientMock clientMock = new GitHubClientMock();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("gitinder://githubcallback?code=7fd23c00de517e3b78c2"));
        intent.setFlags(339738624);
        intent.setComponent(ComponentName.createRelative("com.greenfox.gitinder", ".activities.Login"));
        ActivityController<Login> controller = Robolectric.buildActivity(Login.class, intent).create().start();
        Activity login = controller.get();
        assertTrue(!sharedPreferences.contains(Constants.GITINDER_TOKEN));
        controller.resume();
        Uri uri = login.getIntent().getData();
        ((Login) login).saveGitHubToken(uri, clientMock);
        assertTrue(sharedPreferences.contains(Constants.GITINDER_TOKEN));
    }
}
