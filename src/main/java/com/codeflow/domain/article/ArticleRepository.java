package com.codeflow.domain.article;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.position.Position;

import java.util.List;

public interface ArticleRepository {
    void saveReceived(Article a);

    List<Article> receivedArticles();

    void clear();

    Article findByBoxType(BoxType boxType);

    void removeReceived(Article Article);

    void savePacked(Article Article, Position position);
}
