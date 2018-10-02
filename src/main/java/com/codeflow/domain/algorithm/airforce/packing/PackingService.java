package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.position.Position;

public interface PackingService {
    void pack(ContainerOrientation containerOrientation, ArticleOrientation orientation, Position position);
}
