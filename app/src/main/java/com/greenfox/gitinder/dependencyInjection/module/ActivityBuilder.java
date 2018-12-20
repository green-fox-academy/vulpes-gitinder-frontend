package com.greenfox.gitinder.dependencyInjection.module;

import com.greenfox.gitinder.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//do not touch this class, all is done
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();
}
