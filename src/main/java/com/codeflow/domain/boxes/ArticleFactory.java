package com.codeflow.domain.boxes;

public class ArticleFactory extends Box3DFactory<Article> {

    public ArticleFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory, orientationService);
    }

    @Override
    Article create(Long id, BoxType boxType) {
        return new Article(id, boxType);
    }
}
