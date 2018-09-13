package com.codeflow.domain.article.orientation;

import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;

class ArticleOrientationFactoryImpl implements ArticleOrientationFactory {

    private OrientationFactory orientationFactory;

    ArticleOrientationFactoryImpl(OrientationFactory orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public ArticleOrientation create(Double width, Double height, Double length) {
        Orientation orientation = orientationFactory.create(width, height, length);
        return new ArticleOrientationImpl(orientation);
    }
}
