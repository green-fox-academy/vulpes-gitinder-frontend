package com.greenfox.gitinder.dependencyInjection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.GitinderApp;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.service.GithubAPI;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

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
    GithubAPI githubAPI(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(GithubAPI.class);
    }
}

