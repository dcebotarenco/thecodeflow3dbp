package com.codeflow.domain.boxtype;

import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.orientation.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class BoxTypeRepositoryImpl implements BoxTypeRepository<BoxType> {

    private ArticleRepository articleRepository;

    BoxTypeRepositoryImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<BoxType> boxTypes() {
        return new ArrayList<>(articleRepository.receivedArticles().stream().collect(Collectors.groupingBy(Box3D::getBoxType)).keySet());
    }

    @Override
    public BoxType findByOrientation(Orientation orientation) {
        return boxTypes().stream().filter(boxType -> boxType.getOrientations().stream().anyMatch(typeOrientation -> typeOrientation.equals(orientation))).findFirst().get();
    }
}
