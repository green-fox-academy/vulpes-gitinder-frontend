package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;

public class MatchFactory {

    public static Match createNewMatch() {
        Match match = new Match("Splichus",
                "https://www.biologicaldiversity.org/assets/img/species/mammals/BeardedSealFlickr_foilistpeter.jpg",
                1, new ArrayList<Message>());
        return match;
    }
}
