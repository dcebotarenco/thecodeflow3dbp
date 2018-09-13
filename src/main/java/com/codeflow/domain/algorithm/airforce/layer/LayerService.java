package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.orientation.ContainerOrientation;

import java.util.List;

public interface LayerService {
    List<Layer> listCandidates(ContainerOrientation containerOrientation, List<Article> articles);
}
