package com.greenfox.gitinder.dependencyInjection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.GitinderApp;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

// create your dependency here...
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SharedPreferences sharedPreferences(GitinderApp gitinderApp) {
        return gitinderApp.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
