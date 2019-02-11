package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Languages;

import java.util.ArrayList;
import java.util.List;

public class LanguageFactory {

    public static Languages createLanguages(){
        Languages lang = new Languages();
        List<String> stringList = new ArrayList<>();
        stringList.add("Java");
        stringList.add("PHP");
        stringList.add("C#");
        stringList.add("Javascript");
        stringList.add("React");
        stringList.add("Swift");
        stringList.add("Html");
        stringList.add("CSS");
        lang.setLanguages(stringList);
        return lang;
    }
}
