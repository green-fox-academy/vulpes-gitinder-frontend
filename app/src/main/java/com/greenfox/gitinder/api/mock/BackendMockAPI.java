package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.api.model.LoginResponse;
import com.greenfox.gitinder.api.model.MessageResponse;
import com.greenfox.gitinder.api.model.SwipeResponse;
import com.greenfox.gitinder.api.model.factory.AvailableProfilesFactory;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.Messages;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.User;
import com.greenfox.gitinder.model.factory.ErrorMessageFactory;
import com.greenfox.gitinder.model.factory.MatchFactory;
import com.greenfox.gitinder.model.factory.MessagesFactory;
import com.greenfox.gitinder.model.factory.ProfileFactory;
import com.greenfox.gitinder.model.factory.SettingsFactory;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackendMockAPI implements GitinderAPI {

    final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();
    private int swipeCounter = 0;

    @Override
    public CallMock<LoginResponse> login(final User user) {

        return new CallMock<LoginResponse>() {

            @Override
            public void enqueue(Callback<LoginResponse> callback) {
                LoginResponse loginResponse = new LoginResponse();
                if (user.getUsername() == null) {
                    callback.onResponse(this, Response.<LoginResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Username is missing!"))));
                } else if (user.getAccessToken() == null) {
                    callback.onResponse(this, Response.<LoginResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Access token is missing!"))));
                } else {
                    loginResponse.setStatus("ok");
                    loginResponse.setGitinderToken("abc123");
                    callback.onResponse(this, Response.success(loginResponse));
                }
            }
        };
    }

    @Override
    public CallMock<GitinderResponse> logoutUser(final String header) {

        return new CallMock<GitinderResponse>() {

            @Override
            public void enqueue(Callback<GitinderResponse> callback) {
                GitinderResponse gitinderResponse = new GitinderResponse();
                if (header == null || header.isEmpty()) {
                    callback.onResponse(this, Response.<GitinderResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    gitinderResponse.setStatus("ok");
                    gitinderResponse.setMessage("Logged out successfully!");
                    callback.onResponse(this, Response.success(gitinderResponse));
                }
            }
        };
    }

    @Override
    public CallMock<Settings> getSettings(final String header) {

        return new CallMock<Settings>() {

            @Override
            public void enqueue(Callback<Settings> callback) {

                Settings settings = SettingsFactory.createSettings();

                if (header == null || header.isEmpty()) {
                    callback.onResponse(this, Response.<Settings>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(settings));
                }
            }
        };
    }

    @Override
    public CallMock<GitinderResponse> updateSettings(final String header, final Settings settings) {

        return new CallMock<GitinderResponse>() {

            @Override
            public void enqueue(Callback<GitinderResponse> callback) {
                GitinderResponse apiResponse = new GitinderResponse();
                apiResponse.setStatus("ok");
                apiResponse.setMessage("success");

                if (header == null || header.isEmpty() || settings == null) {
                    callback.onResponse(this, Response.<GitinderResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(apiResponse));
                }
            }
        };
    }

    @Override
    public CallMock<Profile> getProfile(final String gitinderToken) {

        return new CallMock<Profile>() {

            @Override
            public void enqueue(Callback<Profile> callback) {

                Profile profile = ProfileFactory.createProfile();

                if (gitinderToken == null || gitinderToken.isEmpty()) {
                    callback.onResponse(this, Response.<Profile>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(profile));
                }
            }
        };
    }

    @Override
    public Call<AvailableProfiles> getAvailable(final String gitinderToken) {

        return new CallMock<AvailableProfiles>() {
            @Override
            public void enqueue(Callback<AvailableProfiles> callback) {
                AvailableProfiles availableProfiles = AvailableProfilesFactory.createAvailableProfiles();

                if (gitinderToken == null || gitinderToken.isEmpty()) {
                    callback.onResponse(this, Response.<AvailableProfiles>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(availableProfiles));
                }
            }
        };
    }

    @Override
    public Call<SwipeResponse> swipe(final String gitinderToken, String username, String direction) {
        return new CallMock<SwipeResponse>() {
            @Override
            public void enqueue(Callback<SwipeResponse> callback) {
                    SwipeResponse swipeResponse = new SwipeResponse("ok", "success");

                    if (gitinderToken == null || gitinderToken.isEmpty()) {
                        callback.onResponse(this, Response.<SwipeResponse>error(403,
                                ResponseBody.create(MediaType.parse("application/json"),
                                        errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                    } else {
                        if(direction.equals("right")){
                            ++swipeCounter;
                            if(swipeCounter == 2){
                                swipeCounter = 0;
                                swipeResponse.setMatch(MatchFactory.createNewMatch());
                            }
                        }
                        callback.onResponse(this, Response.success(swipeResponse));
                    }
            }
        };
    }

    @Override
    public Call<Matches> matches(final String gitinderToken) {
        return new CallMock<Matches>() {
            @Override
            public void enqueue(Callback<Matches> callback) {
                List<Match> matchesList = MatchFactory.createNewMatches();
                Matches matches = new Matches();
                matches.setMatches(matchesList);

                if (gitinderToken == null || gitinderToken.isEmpty()) {
                    callback.onResponse(this, Response.<Matches>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(matches));
                }
            }
        };
    }

    @Override
    public Call<GitinderResponse> seenProfile(String gitinderToken, String username) {
        return new CallMock<GitinderResponse>() {
            @Override
            public void enqueue(Callback<GitinderResponse> callback) {
                GitinderResponse apiResponse = new GitinderResponse();
                apiResponse.setStatus("ok");
                apiResponse.setMessage("success");

                if (gitinderToken == null || gitinderToken.isEmpty() || username.isEmpty()) {
                    callback.onResponse(this, Response.<GitinderResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(apiResponse));
                }
            }
        };
    }

    @Override
    public Call<Messages> messages(String gitinderToken, String username, int page) {
        return new CallMock<Messages>() {
            @Override
            public void enqueue(Callback<Messages> callback) {
                Messages messages = new Messages();
                messages.setMessage(MessagesFactory.createMessages());
                messages.setCount(messages.getMessage().size());
                messages.setAll(messages.getMessage().size());
                List<Match> matchList = MatchFactory.createNewMatches();
                boolean isMatched = false;

                for (Match match : matchList) {
                    if (match.getUsername().equals(username)) {
                        isMatched = true;
                    }
                }

                if (gitinderToken == null || gitinderToken.isEmpty() || username.isEmpty()) {
                    callback.onResponse(this, Response.<Messages>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else if (!isMatched) {
                    callback.onResponse(this, Response.<Messages>error(404,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Not matched with the user!"))));

                } else {
                    callback.onResponse(this, Response.success(messages));
                }
            }
        };
    }

    @Override
    public Call<MessageResponse> sendMessage(String gitinderToken, String username) {
        return new CallMock<MessageResponse>() {
            @Override
            public void enqueue(Callback<MessageResponse> callback) {
                MessageResponse messageResponse = new MessageResponse();
                Message message = MessagesFactory.createNewMessage();
                messageResponse.setStatus("ok");
                messageResponse.setMessage(message);
                List<Match> matchList = MatchFactory.createNewMatches();
                boolean isMatched = false;

                for (Match match : matchList) {
                    if (match.getUsername().equals(username)) {
                        isMatched = true;
                    }
                }

                if (gitinderToken == null || gitinderToken.isEmpty() || username.isEmpty()) {
                    callback.onResponse(this, Response.<MessageResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else if (!isMatched) {
                    callback.onResponse(this, Response.<MessageResponse>error(404,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Not matched with the user!"))));
                } else {
                    callback.onResponse(this, Response.success(201,messageResponse));
                }
            }
        };
    }
}
