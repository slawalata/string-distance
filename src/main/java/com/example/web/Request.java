package com.example.web;

public class Request {

    private String notebookEntry;

    private String keyword;

    public Request() {
    }

    public Request(String notebookEntry, String keyword) {
        this.notebookEntry = notebookEntry;
        this.keyword = keyword;
    }

    public String getNotebookEntry() {
        return notebookEntry;
    }

    public void setNotebookEntry(String notebookEntry) {
        this.notebookEntry = notebookEntry;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
