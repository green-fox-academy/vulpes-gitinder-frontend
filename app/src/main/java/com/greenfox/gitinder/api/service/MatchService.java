package com.greenfox.gitinder.api.service;

import android.content.SharedPreferences;
import android.util.Log;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.MessageResponse;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;
import com.greenfox.gitinder.model.Messages;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

import static io.fabric.sdk.android.Fabric.TAG;

public class MatchService {
    List<Match> matchList;
    NewMatchCountListener newMatchCountListener;
    MatchesListener matchesListener;

    public MatchService() {
        this.matchList = new ArrayList<>();
    }

    public void addMatches(List<Match> matches){
        matchList.addAll(matches);
        matchesListener.onMatchesChanged(matchList);
        newMatchCountListener.onMatchCountChanged(getNewMatchesCount());
    }

    public void addMatch(Match match){
        matchList.add(match);
        matchesListener.onMatchesChanged(matchList);
        newMatchCountListener.onMatchCountChanged(getNewMatchesCount());
    }

    public void clearMatches(){
        matchList.clear();
        matchesListener.onMatchesChanged(matchList);
        newMatchCountListener.onMatchCountChanged(getNewMatchesCount());
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

    public interface NewMatchCountListener {
        public void onMatchCountChanged(int newMatchCount);
    }

    public interface MatchesListener {
        public void onMatchesChanged(List<Match> updatedMatches);
    }

}
