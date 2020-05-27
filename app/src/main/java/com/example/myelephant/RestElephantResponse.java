package com.example.myelephant;

import java.util.List;

public class RestElephantResponse {
    private Integer count;
    private  String next;
    private List<Elephant> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Elephant> getResults() {
        return results;
    }
}


