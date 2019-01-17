package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileFactory {

    public static Profile createProfile(String username){
        List<String> repos = new ArrayList<>();
        repos.add("repo1"); repos.add("repo2"); repos.add("repo3");
        List<String> languages = new ArrayList<>();
        languages.add("EN"); languages.add("CZ"); languages.add("HU");
        List<String> snippets = new ArrayList<>();
        snippets.add("snippet1"); snippets.add("snippet2"); snippets.add("snippet3");

        return new Profile(username , "/saddgrwehreberv", repos, languages, snippets);
    }

    public static Profile createProfileWithPicture(String username, String picUrl){
        List<String> repos = new ArrayList<>();
        repos.add("repo1"); repos.add("repo2"); repos.add("repo3");
        List<String> languages = new ArrayList<>();
        languages.add("EN"); languages.add("CZ"); languages.add("HU");
        List<String> snippets = new ArrayList<>();
        snippets.add("snippet1"); snippets.add("snippet2"); snippets.add("snippet3");

        return new Profile(username, picUrl, repos, languages, snippets);
    }
}
