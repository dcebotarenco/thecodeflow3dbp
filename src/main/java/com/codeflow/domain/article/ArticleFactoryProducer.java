package com.codeflow.domain.article;

import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.factoryproducer.FactoryProducer;

public class ArticleFactoryProducer implements FactoryProducer<ArticleFactory> {

    private Box3DFactory box3DFactory;

    public ArticleFactoryProducer(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }

    @Override
    public ArticleFactory defaultFactory() {
        return new ArticleFactoryImpl(box3DFactory);
    }
}
