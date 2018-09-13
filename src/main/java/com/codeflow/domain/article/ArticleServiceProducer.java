package com.codeflow.domain.article;

import com.codeflow.domain.serviceproducer.ServiceProducer;

public class ArticleServiceProducer implements ServiceProducer<ArticleService> {

    private ArticleRepository articleRepository;

    public ArticleServiceProducer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleService defaultService() {
        return new ArticleServiceImpl(articleRepository);
    }
}
