package com.codeflow.domain.boxes;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {

    private List<Article> packedArticles;
    private List<Article> receivedArticles;

    public ArticleRepository() {
        this.packedArticles = new ArrayList<>();
        this.receivedArticles = new ArrayList<>();
    }

    public void saveReceived(Article a) {
        this.receivedArticles.add(a);
    }

    public List<Article> receivedArticles() {
        return new ArrayList<>(receivedArticles);
    }

    public void clear(){
        receivedArticles.clear();
        packedArticles.clear();
    }

}
