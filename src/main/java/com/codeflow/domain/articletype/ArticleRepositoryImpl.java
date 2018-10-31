package com.codeflow.domain.articletype;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleRepositoryImpl implements ArticleTypeRepository {

    private Map<ArticleType, Long> receivedArticleTypes;

    public ArticleRepositoryImpl() {
        this.receivedArticleTypes = new LinkedHashMap<>();
    }

    @Override
    public void saveType(ArticleType a, Long number) {
        receivedArticleTypes.merge(a, number, (value, toAdd) -> value + toAdd);
    }

    @Override
    public Map<ArticleType, Long> receivedArticleTypes() {
        return new LinkedHashMap<>(receivedArticleTypes);
    }

    public void clear() {
        receivedArticleTypes.clear();
    }

}
