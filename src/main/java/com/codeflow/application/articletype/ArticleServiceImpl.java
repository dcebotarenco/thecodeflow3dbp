package com.codeflow.application.articletype;

import com.codeflow.application.client.ArticleType;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleTypeRepository articleTypeRepository;

    public ArticleServiceImpl(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
    }

    @Override
    public void create(ArticleType articleType) {
        articleTypeRepository.saveType(new ArticleTypeImpl(articleType.getWidth(),
                articleType.getHeight(),
                articleType.getLength()), articleType.getNumber());
    }

}
