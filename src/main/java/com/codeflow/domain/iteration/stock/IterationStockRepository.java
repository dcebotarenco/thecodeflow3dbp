package com.codeflow.domain.iteration.stock;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.stock.Stock;

import java.util.List;
import java.util.Map;

public interface IterationStockRepository {

    Map<ArticleType, Stock> findAll();

    List<ArticleType> findAllArticleTypes();

    Stock findByArticleType(ArticleType articleType);

    void save(Position position, ArticleOrientation articleOrientation);

    void remove(ArticleType articleType);

    Map<Position, ArticleOrientation> findAllPackedArticles();
}
