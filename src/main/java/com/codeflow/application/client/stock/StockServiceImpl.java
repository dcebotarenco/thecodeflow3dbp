package com.codeflow.application.client.stock;

import com.codeflow.application.client.ArticleType;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;

public class StockServiceImpl implements StockService {

    private final ArticleTypeRepository articleTypeRepository;
    private com.codeflow.domain.stock.StockService stockService;

    public StockServiceImpl(com.codeflow.domain.stock.StockService stockService, ArticleTypeRepository articleTypeRepository) {
        this.stockService = stockService;
        this.articleTypeRepository = articleTypeRepository;
    }

    @Override
    public void create(Stock stock) {
        ArticleType articleType = stock.getArticleType();
        ArticleTypeImpl domainArticleType = new ArticleTypeImpl(articleType.getWidth(),
                articleType.getHeight(),
                articleType.getLength());
        articleTypeRepository.saveType(domainArticleType, stock.getQuantity());
        stockService.createOrUpdate(domainArticleType, stock.getQuantity());
    }

}
