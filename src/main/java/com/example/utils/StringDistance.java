package com.example.utils;

public class StringDistance {

    public int getLevenshteinDistance(String w1, String w2){
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(w1,w2);
    }

}
