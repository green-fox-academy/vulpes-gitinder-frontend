package com.greenfox.gitinder.api.model;

public class TestSetting {
    private boolean getProfiles;
    private boolean swiping;
    private boolean matches;
    private boolean messages;
    private boolean sendMessage;
    private boolean getSettings;
    private boolean setSettings;

    public TestSetting() {
        getProfiles = false;
        swiping = false;
        matches = false;
        messages = false;
        sendMessage = false;
        getSettings = false;
        setSettings = false;
    }

    public boolean isGetProfiles() {
        return getProfiles;
    }

    public void setGetProfiles(boolean getProfiles) {
        this.getProfiles = getProfiles;
    }

    public boolean isSwiping() {
        return swiping;
    }

    public void setSwiping(boolean swiping) {
        this.swiping = swiping;
    }

    public boolean isMatches() {
        return matches;
    }

    public void setMatches(boolean matches) {
        this.matches = matches;
    }

    public boolean isMessages() {
        return messages;
    }

    public void setMessages(boolean messages) {
        this.messages = messages;
    }

    public boolean isSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public boolean isGetSettings() {
        return getSettings;
    }

    public void setGetSettings(boolean getSettings) {
        this.getSettings = getSettings;
    }

    public boolean isSetSettings() {
        return setSettings;
    }

    public void setSetSettings(boolean setSettings) {
        this.setSettings = setSettings;
    }
}
