package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.containertype.ContainerType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LayerServiceTest extends SharedTest {

    @Test
    public void listCandidatesBookExample() {
        ContainerType container = container(104, 96, 84);
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        articles(40, 52, 36, 3);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(0), articleTypeRepository.receivedArticleTypes());
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
        ContainerType container = container(104, 96, 84);
        articles(70, 104, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(0), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(4, layers.size());
        layerAssert(layers, 0, 24, 20);
        layerAssert(layers, 1, 14, 40);
        layerAssert(layers, 2, 70, 44);
        layerAssert(layers, 3, 48, 88);
    }

    @Test
    public void listCandidatesDpp02_0() {
        ContainerType container = container(104, 96, 84);
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(0), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(6, layers.size());
        layerAssert(layers, 0, 50, 20);
        layerAssert(layers, 1, 24, 20);
        layerAssert(layers, 2, 54, 28);
        layerAssert(layers, 3, 48, 32);
        layerAssert(layers, 4, 70, 44);
        layerAssert(layers, 5, 14, 80);
    }

    @Test
    public void listCandidatesDpp02_1() {
        ContainerType container = container(104, 96, 84);
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(1), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(6, layers.size());
        layerAssert(layers, 0, 50, 20);
        layerAssert(layers, 1, 24, 20);
        layerAssert(layers, 2, 54, 28);
        layerAssert(layers, 3, 48, 32);
        layerAssert(layers, 4, 70, 44);
        layerAssert(layers, 5, 14, 80);
    }

    @Test
    public void listCandidatesDpp02_2() {
        ContainerType container = container(104, 96, 84);
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(2), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(5, layers.size());
        layerAssert(layers, 0, 50, 20);
        layerAssert(layers, 1, 24, 20);
        layerAssert(layers, 2, 54, 28);
        layerAssert(layers, 3, 70, 44);
        layerAssert(layers, 4, 104, 272);
    }

    @Test
    public void listCandidatesDpp02_3() {
        ContainerType container = container(104, 96, 84);
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(3), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(5, layers.size());
        layerAssert(layers, 0, 50, 20);
        layerAssert(layers, 1, 24, 20);
        layerAssert(layers, 2, 54, 28);
        layerAssert(layers, 3, 70, 44);
        layerAssert(layers, 4, 104, 272);
    }

    @Test
    public void listCandidatesDpp02_4() {
        ContainerType container = container(104, 96, 84);
        articles(70, 50, 24, 4);
        articles(70, 54, 24, 4);
        articles(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().listCandidates(container.getOrientations().get(4), articleTypeRepository.receivedArticleTypes());
        Assert.assertEquals(6, layers.size());
        layerAssert(layers, 0, 50, 20);
        layerAssert(layers, 1, 24, 20);
        layerAssert(layers, 2, 54, 28);
        layerAssert(layers, 3, 48, 32);
        layerAssert(layers, 4, 70, 44);
        layerAssert(layers, 5, 14, 80);
    }

    private void layerAssert(List<Layer> layers, Integer index, Integer dimension, Integer evaluationValue) {
        Layer layer = layers.get(index);
        Assert.assertEquals(new Double(dimension), layer.getHeight());
        Assert.assertEquals(new Double(evaluationValue), layer.getEvaluationValue());
    }
}