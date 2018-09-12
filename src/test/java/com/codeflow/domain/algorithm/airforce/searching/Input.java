package com.codeflow.domain.algorithm.airforce.searching;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Input {
    private final Map<Integer, List<String>> givenData;
    private final Map<Integer, List<String>> thenData;
    private File test;
    private final Map<Integer, List<String>> articles;

    Input(File test, Map<Integer, List<String>> articles, Map<Integer, List<String>> givenData, Map<Integer, List<String>> thenData) {
        this.test = test;
        this.articles = articles;
        this.givenData = givenData;
        this.thenData = thenData;
    }

    public File getTest() {
        return test;
    }

    public Map<Integer, List<String>> getArticles() {
        return articles;
    }

    public Map<Integer, List<String>> getGivenData() {
        return givenData;
    }

    public Map<Integer, List<String>> getThenData() {
        return thenData;
    }
}
