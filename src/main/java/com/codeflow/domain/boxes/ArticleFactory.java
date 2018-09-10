package com.codeflow.domain.boxes;

import java.util.List;
import java.util.Set;

public class ArticleFactory extends Box3DFactory<Article> {

    public ArticleFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory, orientationService);
    }

    @Override
    Article create(Long id, Dimensions3D dimensions3D, List<Orientation> orientations) {
        return new Article(id, dimensions3D, orientations);
    }
}
