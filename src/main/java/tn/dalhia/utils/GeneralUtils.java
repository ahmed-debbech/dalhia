package tn.dalhia.utils;

import java.util.Arrays;
import java.util.List;

public class GeneralUtils {
    public static List<String> listWords(String text){
        String[] splited = text.split("\\s+");
        return Arrays.asList(splited);
    }
}
