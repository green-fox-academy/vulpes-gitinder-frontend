package com.greenfox.gitinder.dependencyInjection.module;

import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.greenfox.gitinder.fragment.main.SettingsFragment;
import com.greenfox.gitinder.fragment.main.SwipingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract SwipingFragment bindSwipingFragment();

    @ContributesAndroidInjector
    abstract MatchesFragment bindMatchesFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment bindSettingsFragment();

}
