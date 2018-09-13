package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.serviceproducer.ServiceProducer;

public class PackingServiceProducer implements ServiceProducer<PackingService> {

    private BoxTypeRepository boxTypeRepository;
    private ArticleRepository articleRepository;

    @Override
    public PackingService defaultService() {
        return new PackingServiceImpl(boxTypeRepository, articleRepository);
    }
}
