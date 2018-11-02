package com.codeflow.domain.iteration.stock;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.stock.Stock;
import com.codeflow.domain.translator.Translator;

import java.util.List;
import java.util.Map;

public interface IterationStock {

    Map<ArticleType, Stock> findAll();

    void pack(ArticleOrientation foundArticle, Position position);

    Double totalVolume();

    double getPackedVolume();

    List<ArticleType> articleTypes();

    Map<Position, ArticleOrientation> packedArticles();

    Map<Position, ArticleOrientation> translate(Translator translator);
}
