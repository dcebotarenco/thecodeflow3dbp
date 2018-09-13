package com.codeflow.domain.boxtype;

import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class BoxTypeRepositoryProducer implements RepositoryProducer<BoxTypeRepository> {

    private ArticleRepository articleRepository;

    public BoxTypeRepositoryProducer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public BoxTypeRepository defaultRepository() {
        return new BoxTypeRepositoryImpl(articleRepository);
    }
}
