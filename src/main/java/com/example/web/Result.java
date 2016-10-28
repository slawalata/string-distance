package com.example.web;

import java.util.List;

public class Result {

    private String keyword;

    private int frequency;

    private List<String> similarWords;

    public Result(String keyword, int frequency, List<String> similarWords) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.similarWords = similarWords;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getFrequency() {
        return frequency;
    }

    public List<String> getSimilarWords() {
        return similarWords;
    }
}
