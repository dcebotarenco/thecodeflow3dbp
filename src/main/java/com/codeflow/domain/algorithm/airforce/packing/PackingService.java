package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.container.orientation.ContainerOrientation;
import com.codeflow.domain.position.Position;

public interface PackingService {
    void pack(ContainerOrientation containerOrientation, ArticleOrientation orientation, Position position);
}
