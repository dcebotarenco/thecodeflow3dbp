package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;

import java.util.Optional;

public class StockServiceImpl implements StockService {

    StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void createOrUpdate(ArticleType articleType, Long toAdd) {
        Optional<Stock> possibleDomainStock = Optional.ofNullable(stockRepository.findByArticleType(articleType));
        if (possibleDomainStock.isPresent()) {
            possibleDomainStock.get().add(toAdd);
        } else {
            stockRepository.save(new StockImpl(articleType, toAdd));
        }
    }
}
