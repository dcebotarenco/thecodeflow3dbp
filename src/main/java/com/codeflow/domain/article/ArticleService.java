package com.codeflow.domain.article;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.position.Position;

public interface ArticleService {

    void pack(BoxType<ArticleOrientation> boxType, Position position);

    boolean allPacked();
}
