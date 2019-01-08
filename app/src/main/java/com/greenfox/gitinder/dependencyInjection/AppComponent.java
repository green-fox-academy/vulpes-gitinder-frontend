package com.greenfox.gitinder.dependencyInjection;


import android.app.Application;

import com.greenfox.gitinder.GitinderApp;
import com.greenfox.gitinder.dependencyInjection.module.ActivityBuilder;
import com.greenfox.gitinder.dependencyInjection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

//do not touch this class, all is set up, thi class build our dagger injector
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(GitinderApp gitinderApp);
}
