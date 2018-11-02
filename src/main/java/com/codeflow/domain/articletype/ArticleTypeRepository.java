package com.codeflow.domain.articletype;

import java.util.Map;

public interface ArticleTypeRepository {
    void saveType(ArticleType a, Long number);

    Map<ArticleType, Long> receivedArticleTypes();

    void save(ArticleType articleType);


    void clear();
}
