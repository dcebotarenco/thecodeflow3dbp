package com.codeflow.domain.stock;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.stock.IterationStock;
import com.codeflow.domain.iteration.stock.IterationStockImpl;
import com.codeflow.domain.iteration.stock.IterationStockRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class StockServiceImpl implements StockService {

    StockRepository stockRepository;
    private LayerService layerService;

    public StockServiceImpl(StockRepository stockRepository, LayerService layerService) {
        this.stockRepository = stockRepository;
        this.layerService = layerService;
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

    @Override
    public List<Layer> createLayers(ContainerOrientation containerOrientation) {
        return layerService.createLayers(containerOrientation, stockRepository.findAll().values());
    }

    @Override
    public IterationStock createIterationStock() {
        return new IterationStockImpl(new IterationStockRepositoryImpl(stockRepository), layerService);
    }
}
