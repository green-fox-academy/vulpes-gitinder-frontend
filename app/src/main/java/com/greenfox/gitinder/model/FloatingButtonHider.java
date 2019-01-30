package com.greenfox.gitinder.model;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.service.MatchService;

//public class FloatingButtonHider {
//
//    public static void hideFloatingButtonWhenNoNewMatches(MatchService matchService, FloatingActionButton button, TextView buttonText){
//        if(matchService.getNewMatchesCount().equals("0") ||
//           matchService.getNewMatchesCount().equals("") ||
//          !matchService.containsNewMatchesCount()){
//            button.hide();
//            buttonText.setText("");
//        } else {
//            button.show();
//            buttonText.setText(matchService.getNewMatchesCount());
//        }
//    }
//}
