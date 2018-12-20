package com.greenfox.gitinder.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile {

    String username;
    @SerializedName("avatar_url")
    String avatarURI;
    List<String> repos;
    List<String> languages;
    List<String> snippets;
}
