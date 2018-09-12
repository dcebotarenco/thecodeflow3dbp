package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.boxes.Article;
import com.codeflow.domain.boxes.Container;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LayerServiceTest extends SharedTest {



    @Test
    public void listCandidatesBookExample() {
        Container container = container(104, 96, 84);
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        articles(40, 52, 36, 3);
        List<Layer> layers = layerService.listCandidates(container.getOrientations().get(0), articleRepository.receivedArticles());
        Assert.assertEquals(7, layers.size());
        layerAssert(layers, 0, 24, 56);
        layerAssert(layers, 1, 36, 72);
        layerAssert(layers, 2, 52, 80);
        layerAssert(layers, 3, 40, 80);
        layerAssert(layers, 4, 70, 98);
        layerAssert(layers, 5, 48, 100);
        layerAssert(layers, 6, 14, 106);
    }


    @Test
    public void listCandidatesDpp01() {
        Container container = container(104, 96, 84);
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = layerService.listCandidates(container.getOrientations().get(0), articleRepository.receivedArticles());
        Assert.assertEquals(4, layers.size());
        layerAssert(layers, 0, 24, 20);
        layerAssert(layers, 1, 14, 40);
        layerAssert(layers, 2, 70, 44);
        layerAssert(layers, 3, 48, 88);
    }

    public void layerAssert(List<Layer> layers, Integer index, Integer dimension, Integer evaluationValue) {
        Layer layer = layers.get(index);
        Assert.assertEquals(new Double(dimension), layer.getDimension());
        Assert.assertEquals(new Double(evaluationValue), layer.getEvaluationValue());
    }
}