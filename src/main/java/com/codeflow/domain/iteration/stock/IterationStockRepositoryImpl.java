package com.codeflow.domain.iteration.stock;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.stock.Stock;
import com.codeflow.domain.stock.StockImpl;
import com.codeflow.domain.stock.StockRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IterationStockRepositoryImpl implements IterationStockRepository {

    private Map<ArticleType, Stock> stockMap;
    private Map<Position, ArticleOrientation> packedArticles;

    public IterationStockRepositoryImpl(StockRepository stockRepository) {
        stockMap = new LinkedHashMap<>(stockRepository.findAll().size());
        for (Stock s : stockRepository.findAll().values()) {
            StockImpl clonedStock = new StockImpl(s);
            stockMap.put(clonedStock.getArticleType(), clonedStock);
        }
        this.packedArticles = new LinkedHashMap<>();
    }

    public IterationStockRepositoryImpl(Map<ArticleType, Stock> stockRepository) {
        this.stockMap = stockRepository;
        this.packedArticles = new LinkedHashMap<>();
    }

    public Map<ArticleType, Stock> findAll() {
        return new LinkedHashMap<>(stockMap);
    }

    @Override
    public List<ArticleType> findAllArticleTypes() {
        return new ArrayList<>(stockMap.keySet());
    }

    @Override
    public Stock findByArticleType(ArticleType articleType) {
        return stockMap.get(articleType);
    }

    public void save(Position position, ArticleOrientation articleOrientation) {
        packedArticles.put(position, articleOrientation);
    }

    @Override
    public void remove(ArticleType articleType) {
        stockMap.remove(articleType);
    }

    @Override
    public Map<Position, ArticleOrientation> findAllPackedArticles() {
        return new LinkedHashMap<>(packedArticles);
    }
}
