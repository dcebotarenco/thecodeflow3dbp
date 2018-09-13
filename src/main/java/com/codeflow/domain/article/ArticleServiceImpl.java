package com.codeflow.domain.article;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.position.Position;

class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void pack(BoxType<ArticleOrientation> boxType, Position position) {
        Article articleImpl = articleRepository.findByBoxType(boxType);
        articleRepository.removeReceived(articleImpl);
        articleRepository.savePacked(articleImpl, position);
    }

    @Override
    public boolean allPacked() {
        return articleRepository.receivedArticles().isEmpty();
    }
}
