package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;

import java.util.Map;

public interface StockRepository {
    void save(Stock stock);

    Map<ArticleType, Stock> findAll();

    Stock findByArticleType(ArticleType articleType);
}
