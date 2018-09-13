package com.codeflow.domain.article;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.boxtype.BoxType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class ArticleRepositoryImpl implements ArticleRepository {

    private Map<Article, Position> packedArticles;
    private List<Article> receivedArticleImpls;

    ArticleRepositoryImpl() {
        this.packedArticles = new LinkedHashMap<>();
        this.receivedArticleImpls = new ArrayList<>();
    }

    @Override
    public void saveReceived(Article a) {
        this.receivedArticleImpls.add(a);
    }

    @Override
    public List<Article> receivedArticles() {
        return new ArrayList<>(receivedArticleImpls);
    }

    @Override
    public void clear() {
        receivedArticleImpls.clear();
        packedArticles.clear();
    }

    @Override
    public Article findByBoxType(BoxType boxType) {
        return receivedArticleImpls.stream().filter(article -> article.getBoxType().equals(boxType)).findFirst().get();
    }

    @Override
    public void removeReceived(Article Article) {
        receivedArticleImpls.remove(Article);
    }

    @Override
    public void savePacked(Article Article, Position position) {
        packedArticles.put(Article, position);
    }
}
