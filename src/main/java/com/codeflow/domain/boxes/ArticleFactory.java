package com.codeflow.domain.boxes;

public class ArticleFactory extends Box3DFactory<Article> {

    public ArticleFactory(Dimensions3DFactory dimensions3DFactory) {
        super(dimensions3DFactory);
    }

    @Override
    Article create(Long id, Dimensions3D dimensions3D) {
        return new Article(id, dimensions3D);
    }
}
