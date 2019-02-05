package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MatchService {
    List<Match> matchList;
    NewMatchCountListener newMatchCountListener;
    MatchesListener matchesListener;

    public MatchService() {
        this.matchList = new ArrayList<>();
    }

    public void addMatches(List<Match> matches){
        matchList.addAll(matches);
        updateMatches();
    }

    public void addMatch(Match match){
        matchList.add(match);
        updateMatches();
    }

    public void clearMatches(){
        matchList.clear();
        updateMatches();
    }

    public void updateMatches(){
        matchesListener.onMatchesChanged(matchList);
        newMatchCountListener.onMatchCountChanged(getNewMatchesCount());
    }

    public int getNewMatchesCount(){
        int counter = 0;
        for(Match matchCurrent : matchList){
            if(matchCurrent.isNew()){
                counter++;
            }
        }
        return counter;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setNewMatchCountListener(NewMatchCountListener countListener){
        this.newMatchCountListener = countListener;
    }

    public void setMatchesListener (MatchesListener matchesListener){
        this.matchesListener = matchesListener;
    }

    public List<Message> getMessagesByUsername(String username){
        List<Message> messageList = new ArrayList<>();
        for(Match currentMatch : matchList){
            if(currentMatch.getUsername().equals(username)){
                messageList = currentMatch.getMessages();
            }
        }
        return messageList;
    }

    public void setMessagesByUsername(String username, List<Message> messageList){
        for(Match currentMatch : matchList){
            if(currentMatch.getUsername().equals(username)){
                currentMatch.setMessages(messageList);
            }
        }
        updateMatches();
    }

    public interface NewMatchCountListener {
        public void onMatchCountChanged(int newMatchCount);
    }

    public interface MatchesListener {
        public void onMatchesChanged(List<Match> updatedMatches);
    }

}
