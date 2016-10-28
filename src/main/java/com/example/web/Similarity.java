package com.example.web;

import java.util.List;

public class Similarity {

    private String keyword;

    private int frequency;

    private List<String> similarWords;

    public Similarity(String keyword, int frequency, List<String> similarWords) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.similarWords = similarWords;
    }

    public Similarity() {
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
