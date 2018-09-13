package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.article.ArticleService;
import com.codeflow.domain.container.orientation.ContainerOrientation;

import java.util.List;
import java.util.ListIterator;

public class OrientationIteration implements Runnable {

    private Long iterationsCount;
    private ContainerOrientation containerOrientation;
    private ArticleService articleService;
    private List<LayerIteration> layerIterations;


    OrientationIteration(ContainerOrientation containerOrientation,
                         List<LayerIteration> layerIterations,
                         ArticleService articleService) {
        this.layerIterations = layerIterations;
        this.containerOrientation = containerOrientation;
        this.articleService = articleService;
        iterationsCount = 0L;
    }

    @Override
    public void run() {

        ListIterator<LayerIteration> iterator = layerIterations.listIterator();
        while (iterator.hasNext() || !containerOrientation.allVolumePacked() || !articleService.allPacked()) {
            iterator.next().run(containerOrientation);
        }
    }

    public ContainerOrientation getContainerOrientation() {
        return containerOrientation;
    }
}
