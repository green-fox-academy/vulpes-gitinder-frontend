package com.greenfox.gitinder.dependencyInjection.module;

import com.greenfox.gitinder.activities.MainActivity;
import com.greenfox.gitinder.activities.SettingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//add ContributesAndroidInjector with the name of your activity with inject annotation
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract SettingsActivity bindSettingsActivity();
}
