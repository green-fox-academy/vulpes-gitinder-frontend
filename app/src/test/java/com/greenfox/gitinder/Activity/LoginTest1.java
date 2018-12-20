package com.greenfox.gitinder.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.DialogFragment;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.api.mock.BackendMockAPI;
import com.greenfox.gitinder.api.mock.GitHubMock;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.factory.IntentFactory;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LoginTest1 {

    Login login;
    SharedPreferences preferences;

    @Test
    public void dialogFragmentIsShownToTheUser() {
        preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ryba", "aaa").apply();
        login = Robolectric.setupActivity(Login.class);
        login.onResume();
        DialogFragment dialogFragment = (DialogFragment) login.getSupportFragmentManager().findFragmentByTag("loginDialog");
        assertNotNull(dialogFragment);
    }
    @Test
    public void testRedirectTrue() {
        preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GITINDER_TOKEN, "aaa").apply();
        login = Robolectric.setupActivity(Login.class);
        Intent expectedIntent = new Intent(login, MainActivity.class);
        Intent actual = IntentFactory.getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
    @Test
    public void testRedirectFail() {
        preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ryba", "aaa").apply();
        Intent actual = IntentFactory.getNextStartedActivity();
        assertEquals(null, actual);
    }
    @Test
    public void testButtonWillRedirect() {
        login = Robolectric.setupActivity(Login.class);
        login.login.performClick();
        Intent expectedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="+Constants.GITHUB_CLIENT_ID+"&redirect_uri="+Constants.GITHUB_CALLBACK));
        Intent actual = IntentFactory.getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
    @Test
    public void testMockIsReturningAccessToken() {
        GitHubMock clientMock = new GitHubMock();
        Call<GitHubToken> call = clientMock.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, "7fd23c00de517e3b78c2");
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
        preferences = SharedPreferencesFactory.getSharedPref();
        GitHubMock clientMock = new GitHubMock();
        Intent intent = IntentFactory.getGitHubCallBackIntent();
        ActivityController<Login> controller = Robolectric.buildActivity(Login.class, intent).create().start();
        Activity login = controller.get();
        assertTrue(!preferences.contains(Constants.GITINDER_TOKEN));
        controller.resume();
        Uri uri = login.getIntent().getData();
        ((Login) login).saveGitHubToken(uri, clientMock);
        assertTrue(preferences.contains(Constants.GITINDER_TOKEN));
    }
}
