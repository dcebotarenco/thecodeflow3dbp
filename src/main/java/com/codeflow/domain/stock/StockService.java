package com.codeflow.domain.stock;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.stock.IterationStock;

import java.util.List;

public interface StockService {

    void createOrUpdate(ArticleType articleType, Long toAdd);

    List<Layer> createLayers(ContainerOrientation containerOrientation);

    IterationStock createIterationStock();
}
