package com.codeflow.domain.iteration.stock;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.stock.Stock;
import com.codeflow.domain.translator.Translator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IterationStockImpl implements IterationStock {

    private IterationStockRepository iterationStockRepository;
    private LayerService layerService;
    private double packedVolume;

    public IterationStockImpl(IterationStockRepository iterationStockRepository,
                              LayerService layerService) {
        this.iterationStockRepository = iterationStockRepository;
        this.layerService = layerService;
        this.packedVolume = 0L;
    }

    public Map<ArticleType, Stock> findAll() {
        return iterationStockRepository.findAll();
    }

    @Override
    public void pack(ArticleOrientation foundArticle, Position position) {
        ArticleType articleType = foundArticle.getBoxType();
        Stock stock = iterationStockRepository.findByArticleType(articleType);
        stock.withdraw(1);
        if (stock.getQuantity() == 0) {
            iterationStockRepository.remove(stock.getArticleType());
        }
        iterationStockRepository.save(position, foundArticle);
        packedVolume = packedVolume + foundArticle.getVolume();
    }

    @Override
    public Double totalVolume() {
        return iterationStockRepository.findAll().values().stream().map(s -> s.getArticleType().getVolume() * s.getQuantity()).reduce((v1, v2) -> v1 + v2).orElse(0D);
    }

    @Override
    public double getPackedVolume() {
        return packedVolume;
    }

    public List<ArticleType> articleTypes() {
        return this.iterationStockRepository.findAllArticleTypes();
    }

    public Map<Position, ArticleOrientation> packedArticles() {
        return this.iterationStockRepository.findAllPackedArticles();
    }

    @Override
    public Map<Position, ArticleOrientation> translate(Translator translator) {
        Map<Position, ArticleOrientation> translated = new LinkedHashMap<>();
        for (Map.Entry<Position, ArticleOrientation> entry : this.packedArticles().entrySet()) {
            Position pp = entry.getKey();
            ArticleOrientation aa = entry.getValue();
            Position p = translator.translate(pp);
            ArticleOrientation a = translator.translate(aa);
            translated.put(p, a);
        }
        return translated;
    }

    @Override
    public Optional<Layer> findNextLayer(ContainerOrientation containerOrientation, Double containerRemainingHeight) {
        return layerService.findLayer(containerOrientation, containerRemainingHeight, findAll().values());
    }
}
