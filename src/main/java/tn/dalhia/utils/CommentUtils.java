package tn.dalhia.utils;

import tn.dalhia.entities.OffensiveWord;

import java.util.List;

public class CommentUtils {
    public static boolean isFine(String val, List<OffensiveWord> badWords){

        String[] splited = val.split("\\s+");
        String newval = "";
        for(String word : splited){
            String stars = "";
            for(OffensiveWord bad : badWords){
                if(word.toLowerCase().equals(bad.getWord().toLowerCase())){
                    return false;
                }
            }
        }
        return true;
    }
}
