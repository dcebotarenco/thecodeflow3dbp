package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.ContainerTypeImpl;
import com.codeflow.domain.stock.Stock;
import com.codeflow.domain.stock.StockImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LayerServiceTest extends SharedTest {


    List<Stock> stock;
    ContainerType container;

    @Before
    public void clearRepos() {
        stock = new ArrayList<>();
    }

    void stock(Integer w, Integer h, Integer l, Integer number) {
        stock.add(new StockImpl(new ArticleTypeImpl(w, h, l), number));
    }

    ContainerType container(int w, int h, int l) {
        container = new ContainerTypeImpl(w, h, l);
        return container;
    }

    @Test
    public void listCandidatesBookExample() {

        ContainerTypeImpl container = new ContainerTypeImpl(104, 96, 84);
        stock(70, 104, 24, 4);
        stock(14, 104, 48, 2);
        stock(40, 52, 36, 3);

        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(0), stock);
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
        stock(70, 104, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(0), stock);
        Assert.assertEquals(4, layers.size());
        layerAssert(layers, 0, 24, 20);
        layerAssert(layers, 1, 14, 40);
        layerAssert(layers, 2, 70, 44);
        layerAssert(layers, 3, 48, 88);
    }

    @Test
    public void listCandidatesDpp02_0() {
        ContainerType container = container(104, 96, 84);
        stock(70, 50, 24, 4);
        stock(70, 54, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(0), stock);
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
        stock(70, 50, 24, 4);
        stock(70, 54, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(1), stock);
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
        stock(70, 50, 24, 4);
        stock(70, 54, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(2), stock);
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
        stock(70, 50, 24, 4);
        stock(70, 54, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(3), stock);
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
        stock(70, 50, 24, 4);
        stock(70, 54, 24, 4);
        stock(14, 104, 48, 2);
        List<Layer> layers = new LayerServiceImpl().createLayers(container.getOrientations().get(4), stock);
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