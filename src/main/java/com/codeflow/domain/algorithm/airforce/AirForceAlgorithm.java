package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.Result;
import com.codeflow.domain.algorithm.airforce.topology.Topology;
import com.codeflow.domain.boxes.Article;
import com.codeflow.domain.boxes.Container;

import java.util.List;

public class AirForceAlgorithm implements Algorithm {

    Double totalContainerVolume;
    Double totalItemVolume;
    Double bestVolume;
    Boolean packingBest;
    Boolean hundredPercentPacked;
    Long iterationsCount;
    Boolean quit;
    Topology topology;

    @Override
    public Result run(Container container, List<Article> articles) {

        totalContainerVolume = container.getWidth() * container.getHeight() * container.getHeight();
        totalItemVolume = articles.stream().map(article -> article.getWidth() * article.getHeight() * article.getLength())
                .reduce((v1, v2) -> v1 + v2).orElseThrow(() -> new IllegalStateException("Cannot calculate total volume of Items"));
        bestVolume = 0.0;
        packingBest = false;
        hundredPercentPacked = false;
        iterationsCount = 0L;
        quit = false;
        topology = new Topology();

        while (!quit) {

        }


        return null;
    }
}
