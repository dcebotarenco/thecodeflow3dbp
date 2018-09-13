package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.container.orientation.ContainerOrientation;

public interface PackingService {
    void pack(ContainerOrientation containerOrientation, ArticleOrientation orientation, Position position);
}