package com.greenfox.gitinder.dependencyInjection.module;

import com.greenfox.gitinder.activity.Login;
import com.greenfox.gitinder.activity.MainActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//add ContributesAndroidInjector with the name of your activity with inject annotation
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract Login bindLogin();

}
