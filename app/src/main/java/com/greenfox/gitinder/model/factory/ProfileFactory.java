package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileFactory {

    public static Profile createProfile(){
        String username = getUsername();
        List<String> repos = getRepos();
        List<String> languages = getLanguages();
        List<String> snippets = getSnippets();
        String picUrl = getPicUri();

        return new Profile(username , picUrl, repos, languages, snippets);
    }

    private static List<String> getLanguages() {
        List<String> allLanguages = new ArrayList<>(Arrays.asList("APL", "AutoIt", "BASIC", "Eiffel", "Forth", "Frink", "Game Maker Language", "ICI", "J", "Lisp", "Lua", "Pascal",
         "PCASTL", "Perl", "PostScript", "Python", "REXX", "Ruby", "S-Lang", "Spin", "Charity", "Clean", "Curry", "Erlang", "F#", "Haskell", "Joy", "Kite", "ML", "Nemerle", "OPAL",
        "OPS5", "Q", "Ada", "ALGOL", "C", "C++", "C#", "CLEO", "COBOL", "Cobra", "D", "DASL", "DIBOL", "Fortran", "Java", "JOVIAL", "Objective-C", "SMALL", "Smalltalk", "Turing",
        "Visual Basic", "Visual FoxPro", "XL", "Bliss", "ChucK", "CLIST", "HyperTalk", "Modula-2", "Oberon", "Component Pascal", "MATLAB", "Occam", "PL/C", "PL/I", "Rapira", "RPG",
                "AppleScript", "Awk", "BeanShell", "ColdFusion", "F-Script", "JASS", "Mondrian", "PHP", "Revolution", "Tcl", "VBScript", "Windows PowerShell", "Curl", "SGML", "HTML",
                "XML", "XHTML", "ALF", "Fril", "Janus", "Leda", "Oz", "Poplog", "Prolog", "ROOP", "ABCL", "Afnix", "Cilk", "Concurrent Pascal", "E", "Joule", "Limbo", "Pict",
                "SALSA", "SR", "Agora", "BETA", "Cecil", "Lava", "Lisaac", "MOO", "Moto", "Object-Z", "Obliq", "Oxygene", "Pliant", "Prograph", "REBOL", "Scala", "Self", "Slate", "IO"));
        int nOfLang = (int) (Math.random()*5) + 1;
        int ranLang;
        List<String> toReturn = new ArrayList<>();
        for (int i = 0; i < nOfLang ; i++) {
            ranLang = (int) (Math.random()*allLanguages.size());
            toReturn.add(allLanguages.get(ranLang));
            allLanguages.remove(ranLang);
        }
        return toReturn;
    }
    private static List<String> getSnippets() {
        List<String> snipets = new ArrayList<>(Arrays.asList(""));
        return snipets;
    }
    private static List<String> getRepos() {
        List<String> allRepos = new ArrayList<>(Arrays.asList("Operse", "loggrash", "jclocken", "drunlog", "simpling", "erlint", "Kickit", "esmyscrib", "sermine", "roomgraph"));
        int nOfRepo = (int) (Math.random()*5) + 1;
        int ranRepo;
        List<String> toReturn = new ArrayList<>();
        for (int i = 0; i < nOfRepo ; i++) {
            ranRepo = (int) (Math.random()*allRepos.size());
            toReturn.add(allRepos.get(ranRepo));
        }
        return toReturn;
    }
    private static String getPicUri() {
        List<String> allPictures = new ArrayList<>(Arrays.asList(
                "http://s1.1zoom.me/b6742/381/Sky_Night_Moon_Clouds_542827_600x800.jpg",
                "https://www.noao.edu/image_gallery/images/d4/conjuncionV-600.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/4/4d/Paul_Schneider_Dickenschied_Kirche_600x800.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/0/05/Lippstadt-Nicolaikirche-600x800-schwarzweiss-4.jpg",
                "http://noomoon.com/noomoonastro/AstroGallery/images/HorseHeadFlame.20040227.22X2Mins.10X2Mins.10X2Mins.16X2Mins.600X800.jpg",
                "http://ywwg.com/images/photos/072203-film/0009-ar.600x800.jpg",
                "https://mmsmady.files.wordpress.com/2012/02/ice-1-600x800.jpg",
                "http://alqabasdemo1.mawaqaatest.com/uploads/news/in_category/6228_eiffel_tower_at_night_600x800.jpg",
                "https://www.cestujsnadno.cz/wp-content/uploads/2018/11/burj-al-arab-690768_1920-600x800.jpg",
                "http://www.agrall.cz/gal/1385640072.jpg",
                "http://travelistan.sk/wp-content/uploads/2015/11/091-600x800.jpg",
                "http://primitivniumeni.cz/wp-content/uploads/2018/09/sosky-bulul-filipiny-portf4-600x800.jpg",
                "http://www.praha4.cz/image/ov/jcb31/tsk-cisteni-komunikaci-02.JPG",
                "http://www.showtheroom.com/wp-content/uploads/2018/06/Saty-Do-sve%CC%8Cta-600x800.jpg",
                "https://www.tynadraw.cz/user/documents/upload/BLOG%20OBR%C3%81ZKY/RipNDip-Lord-Nermal-T-Shirt_4-600x800.jpg",
                "http://www.jomagazin.cz/wp-content/uploads/2018/02/27848310_2084068738276460_631553093_n201-600x800.jpg",
                "https://www.logline.cz/ll/assets/images/josifkova-600x800.png"));
        int ranPic = (int) (Math.random()*allPictures.size());
        return allPictures.get(ranPic);
    }
    private static String getUsername() {
        List<String> allUsernames = new ArrayList<>(Arrays.asList("Danny", "Maara", "Splichus", "Aze", "Magda", "Matka", "Ondrej", "Fox", "Greenfox", "Jablko", "IloveJAVA",
                "Android123", "Destroyer99", "GitinderUser1", "MrHashtag", "TheKotlinLover", "SwiftIsMyLife"));
        int ranNick = (int) (Math.random()*allUsernames.size());
        return allUsernames.get(ranNick);
    }
}
