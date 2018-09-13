package com.codeflow.domain.article.orientation;

import com.codeflow.domain.factoryproducer.FactoryProducer;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;

public class ArticleOrientationFactoryProducer implements FactoryProducer<ArticleOrientationFactory> {

    private OrientationFactory<Orientation> orientationFactory;

    public ArticleOrientationFactoryProducer(OrientationFactory<Orientation> orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public ArticleOrientationFactory defaultFactory() {
        return new ArticleOrientationFactoryImpl(orientationFactory);
    }
}
