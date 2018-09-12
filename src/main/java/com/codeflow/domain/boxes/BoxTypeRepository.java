package com.codeflow.domain.boxes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoxTypeRepository {

    private ArticleRepository articleRepository;

    public BoxTypeRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<BoxType> boxTypes() {
        return new ArrayList<>(articleRepository.receivedArticles().stream().collect(Collectors.groupingBy(Box3D::getBoxType)).keySet());
    }
}
