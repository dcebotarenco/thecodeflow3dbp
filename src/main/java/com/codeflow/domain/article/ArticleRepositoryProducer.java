package com.codeflow.domain.article;

import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class ArticleRepositoryProducer implements RepositoryProducer<ArticleRepository> {
    @Override
    public ArticleRepository defaultRepository() {
        return new ArticleRepositoryImpl();
    }
}
