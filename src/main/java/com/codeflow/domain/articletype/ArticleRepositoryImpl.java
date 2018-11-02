package com.codeflow.domain.articletype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepositoryImpl implements ArticleTypeRepository {

    private Map<ArticleType, Long> receivedArticleTypes;
    private List<ArticleType> articleTypes;

    public ArticleRepositoryImpl() {
        this.receivedArticleTypes = new LinkedHashMap<>();
        this.articleTypes = new ArrayList<>();
    }

    @Override
    public void saveType(ArticleType a, Long number) {
        receivedArticleTypes.merge(a, number, (value, toAdd) -> value + toAdd);
    }

    @Override
    public Map<ArticleType, Long> receivedArticleTypes() {
        return new LinkedHashMap<>(receivedArticleTypes);
    }

    @Override
    public void save(ArticleType articleType) {
        this.articleTypes.add(articleType);
    }

    public void clear() {
        receivedArticleTypes.clear();
    }

}
