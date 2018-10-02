package com.codeflow.domain.articletype;

import java.util.List;
import java.util.Map;

public interface ArticleTypeRepository {
    void saveType(ArticleType a, Long number);

    List<ArticleType> unpackedTypes();

    void savePack(ArticleType articleType);

    Map<ArticleType, Long> receivedArticleTypes();

    boolean areAllPacked();

    void reset();

    void clear();
}
