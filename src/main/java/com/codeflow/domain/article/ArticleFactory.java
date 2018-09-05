package com.codeflow.domain.article;

public class ArticleFactory {
    public Article createArticle(Long id, Double width, Double length, Double height) {
        return new Article(id, width, length, height);
    }
}
