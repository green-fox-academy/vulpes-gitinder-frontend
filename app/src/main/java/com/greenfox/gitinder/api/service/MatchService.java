package com.greenfox.gitinder.api.service;

import android.content.SharedPreferences;

import com.greenfox.gitinder.model.Match;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MatchService {

    @Inject
    GitinderAPI gitinderAPI;

    @Inject
    SharedPreferences sharedPreferences;

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

    public void clearMatches(){
        matchList.clear();
        matchesListener.onMatchesChanged(matchList);
        newMatchCountListener.onMatchCountChanged(getNewMatchesCount());
    }

    public void updateNewMatchesCount(){
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