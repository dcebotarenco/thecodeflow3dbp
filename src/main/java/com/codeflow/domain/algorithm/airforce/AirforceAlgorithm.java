package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.Result;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.Container;

import java.util.List;

public class AirforceAlgorithm implements Algorithm {

    Double totalContainerVolume;
    Double totalItemVolume;

    @Override
    public Result run(Container container, List<Article> articles) {

        totalContainerVolume = container.getWidth() * container.getHeight() * container.getHeight();
        totalItemVolume = articles.stream().map(article -> article.getWidth() * article.getHeight() * article.getLength())
                .reduce((v1, v2) -> v1 + v2).orElseThrow(() -> new IllegalStateException("Cannot calculate total volume of Items"));

        return null;
    }
}
