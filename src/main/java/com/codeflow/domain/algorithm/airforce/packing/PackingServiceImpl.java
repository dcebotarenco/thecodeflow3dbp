package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.articletype.ArticleService;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.position.Position;

public class PackingServiceImpl implements PackingService {

    private ArticleService articleService;

    public PackingServiceImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void pack(ContainerOrientation containerOrientation, ArticleOrientation articleOrientation, Position position) {
        articleService.pack(articleOrientation, position);
        containerOrientation.pack(articleOrientation);
    }
}
