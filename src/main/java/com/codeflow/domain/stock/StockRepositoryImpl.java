package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;

import java.util.LinkedHashMap;
import java.util.Map;

public class StockRepositoryImpl implements StockRepository {

    private Map<ArticleType, Stock> stockMap;

    public StockRepositoryImpl() {
        this.stockMap = new LinkedHashMap<>();
    }

    @Override
    public void save(Stock stock) {
        this.stockMap.put(stock.getArticleType(), stock);
    }

    public Map<ArticleType, Stock> findAll() {
        return new LinkedHashMap<>(stockMap);
    }

    @Override
    public Stock findByArticleType(ArticleType articleType) {
        return stockMap.get(articleType);
    }
}
