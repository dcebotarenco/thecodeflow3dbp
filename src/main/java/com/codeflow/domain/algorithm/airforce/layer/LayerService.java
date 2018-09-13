package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface LayerService {
    List<Layer> listCandidates(Orientation containerOrientation, List<Article> articleImpls);
}
