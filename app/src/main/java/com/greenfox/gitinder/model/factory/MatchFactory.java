package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MatchFactory {

    public static List<Match> createNewMatches() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Splichus",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        matches.add(new Match("Trojebus",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        matches.add(new Match("Milos",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        matches.add(new Match("Petrtyl",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        matches.add(new Match("Franta",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        matches.add(new Match("Markus",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMgPU9FD3ro1rFqB2tyaIcm8Z48KTH1yB5awtQG1YVK-0svqy4",
                1, new ArrayList<Message>()));
        return matches;
    }

    public static Match createNewMatch() {
        Match match = new Match("Splichus",
                "https://www.biologicaldiversity.org/assets/img/species/mammals/BeardedSealFlickr_foilistpeter.jpg",
                1, new ArrayList<Message>());
        return match;
    }
}
