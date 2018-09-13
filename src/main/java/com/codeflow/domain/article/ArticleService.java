package com.codeflow.domain.article;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.boxtype.BoxType;

public interface ArticleService {

    void pack(BoxType<ArticleOrientation> boxType, Position position);

    boolean allPacked();
}
