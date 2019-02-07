package com.greenfox.gitinder.dependencyInjection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.mock.BackendMockAPI;
import com.greenfox.gitinder.api.model.TestSetting;
import com.greenfox.gitinder.api.service.GithubAPI;
import com.greenfox.gitinder.api.service.GithubTokenAPI;
import com.greenfox.gitinder.api.service.GitinderAPI;


import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.api.service.MessageService;
import com.greenfox.gitinder.api.service.SnippetService;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.service.NotificationService;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

// create your dependency here. Provides annotation have to return object cannot be done on void methods.

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences sharedPreferences(Application application) {
        return application.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Settings settings() {
        return new Settings();
    }

    @Provides
    @Singleton
    GithubAPI githubUserAPI(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(GithubAPI.class);
    }

    @Provides
    @Singleton
    GithubTokenAPI githubAPI(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(GithubTokenAPI.class);
    }

    @Provides
    @Singleton
    SnippetService snippetService() {
        return new SnippetService();
    }

    @Provides
    @Singleton
    GitinderAPI realApi() {
        return new Retrofit.Builder().baseUrl(Constants.GITINDER_API_LIVE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(GitinderAPI.class);
    }

    @Provides
    @Singleton
    BackendMockAPI mockApi() {
        return new BackendMockAPI();
    }

    @Provides
    @Singleton
    NotificationService notificationService() {
        return new NotificationService();
    }

    @Provides
    @Singleton
    @Inject
    MatchService matchService(NotificationService notificationService) {
        return new MatchService(notificationService);
    }

    @Provides
    @Singleton
    TestSetting testSettings() {
        return new TestSetting();
    }

    @Provides
    @Singleton
    @Inject
    GitinderAPIService gitinderAPIService(GitinderAPI realAPI, BackendMockAPI mockAPI, TestSetting testSettings) {
        return new GitinderAPIService(realAPI, mockAPI, testSettings);
    }
}
