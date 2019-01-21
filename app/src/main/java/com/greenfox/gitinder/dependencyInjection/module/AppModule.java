package com.greenfox.gitinder.dependencyInjection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.mock.BackendMockAPI;
import com.greenfox.gitinder.api.service.GithubAPI;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.SnippetService;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.Settings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    GithubAPI githubAPI(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(GithubAPI.class);
    }

    @Provides
    @Singleton
    GitinderAPI backendAPI() {
        if (BuildConfig.FLAVOR.equals("live")) {
            return getApi("https://gitinder.azurewebsites.net");
        } else if (BuildConfig.FLAVOR.equals("staging")) {
            return getApi("https://gitinder-staging.azurewebsites.net");
        } else {
            return new BackendMockAPI();
        }
    }
    @Provides
    @Singleton
    SnippetService snippetService() {
        return new SnippetService();
    }

    private GitinderAPI getApi(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(GitinderAPI.class);
    }
}
