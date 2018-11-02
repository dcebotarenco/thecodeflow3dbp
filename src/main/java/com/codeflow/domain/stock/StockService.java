package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;

public interface StockService {

    void createOrUpdate(ArticleType articleType, Long toAdd);
}
