package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LayerService {
    List<Layer> listCandidates(ContainerOrientation containerOrientation, Map<ArticleType, Long> articleTypes);

    Optional<Layer> findLayer(ContainerOrientation containerOrientation, Double height, Map<ArticleType, Long> articleTypes);
}
