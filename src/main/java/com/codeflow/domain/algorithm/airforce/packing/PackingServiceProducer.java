package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.article.ArticleService;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.serviceproducer.ServiceProducer;

public class PackingServiceProducer implements ServiceProducer<PackingService> {

    private BoxTypeRepository boxTypeRepository;
    private ArticleService articleService;

    public PackingServiceProducer(BoxTypeRepository boxTypeRepository, ArticleService articleService) {
        this.boxTypeRepository = boxTypeRepository;
        this.articleService = articleService;
    }

    @Override
    public PackingService defaultService() {
        return new PackingServiceImpl(boxTypeRepository, articleService);
    }
}
