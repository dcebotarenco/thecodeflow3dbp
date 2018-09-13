package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.container.orientation.ContainerOrientation;

class PackingServiceImpl implements PackingService {

    private BoxTypeRepository boxTypeRepository;
    private ArticleRepository articleRepository;

    PackingServiceImpl(BoxTypeRepository boxTypeRepository, ArticleRepository articleRepository) {
        this.boxTypeRepository = boxTypeRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void pack(ContainerOrientation containerOrientation, ArticleOrientation articleOrientation, Position position) {
        BoxType boxType = boxTypeRepository.findByOrientation(articleOrientation);
        Article articleImpl = articleRepository.findByBoxType(boxType);
        articleRepository.removeReceived(articleImpl);
        articleRepository.savePacked(articleImpl, position);
        containerOrientation.pack(articleOrientation);
    }
}
