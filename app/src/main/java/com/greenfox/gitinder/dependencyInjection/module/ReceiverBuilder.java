package com.greenfox.gitinder.dependencyInjection.module;

import com.greenfox.gitinder.api.reciever.BackgroundReceiver;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReceiverBuilder {

    @ContributesAndroidInjector
    abstract BackgroundReceiver bindBackgroundReceiver();
}
