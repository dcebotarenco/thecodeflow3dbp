package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.article.ArticleService;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.container.orientation.ContainerOrientation;

class PackingServiceImpl implements PackingService {

    private BoxTypeRepository<BoxType<ArticleOrientation>> boxTypeRepository;
    private ArticleService articleService;

    PackingServiceImpl(BoxTypeRepository<BoxType<ArticleOrientation>> boxTypeRepository,
                       ArticleService articleService) {
        this.boxTypeRepository = boxTypeRepository;
        this.articleService = articleService;
    }

    @Override
    public void pack(ContainerOrientation containerOrientation, ArticleOrientation articleOrientation, Position position) {
        BoxType<ArticleOrientation> articleOrientationBoxType = boxTypeRepository.findByOrientation(articleOrientation);
        articleService.pack(articleOrientationBoxType, position);
        containerOrientation.pack(articleOrientation);
    }
}
