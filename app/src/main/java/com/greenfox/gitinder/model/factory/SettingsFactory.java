package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory {

    public Settings createSettings(){
        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Kotlin");
        languages.add("Android SDK");
        return new Settings(true, true, 0, languages);
    }
}
