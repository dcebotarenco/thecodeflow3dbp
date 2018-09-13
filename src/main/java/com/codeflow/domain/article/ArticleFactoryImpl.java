package com.codeflow.domain.article;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.box.Box3DFactory;

import java.util.Random;

class ArticleFactoryImpl implements ArticleFactory {

    private Box3DFactory box3DFactory;

    ArticleFactoryImpl(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }

    public Article create(Long id, Double width, Double length, Double height) {
        Box3D box3D = box3DFactory.create(id, width, height, length);
        return new ArticleImpl(box3D);
    }

    @Override
    public Article create(Double width, Double height, Double length) {
        Box3D box3D = box3DFactory.create((long) new Random().nextInt(100), width, height, length);
        return new ArticleImpl(box3D);
    }
}
