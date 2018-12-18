package com.greenfox.gitinder.Model.Factory;

import com.greenfox.gitinder.Model.Settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsFactory {

    public Settings createSettings(){
        List<String> languages = new ArrayList<>();
        return new Settings(true, true, 0, languages);
    }
}
