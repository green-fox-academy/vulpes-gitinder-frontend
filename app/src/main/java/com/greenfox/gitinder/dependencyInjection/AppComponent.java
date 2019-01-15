package com.greenfox.gitinder.dependencyInjection;

import android.app.Application;

import com.greenfox.gitinder.GitinderApp;
import com.greenfox.gitinder.dependencyInjection.module.ActivityBuilder;
import com.greenfox.gitinder.dependencyInjection.module.AppModule;
import com.greenfox.gitinder.dependencyInjection.module.FragmentBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//do not touch this class, all is set up, thi class build our dagger injector
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                      AppModule.class,
                      ActivityBuilder.class,
                      FragmentBuilder.class})

public interface AppComponent extends AndroidInjector<GitinderApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(GitinderApp gitinderApp);
}
