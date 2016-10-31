package com.example.web;

import java.util.List;
import java.util.Objects;

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

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<String> getSimilarWords() {
        return similarWords;
    }

    public void setSimilarWords(List<String> similarWords) {
        this.similarWords = similarWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Similarity that = (Similarity) o;
        return frequency == that.frequency &&
                Objects.equals(keyword, that.keyword) &&
                Objects.equals(similarWords, that.similarWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword, frequency, similarWords);
    }
}
