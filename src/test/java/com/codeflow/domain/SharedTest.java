package com.codeflow.domain;

import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.boxes.*;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public class SharedTest {

    public static Dimensions3DFactory dimensions3DFactory;
    public static OrientationFactory orientationFactory;
    public static OrientationService orientationService;
    public static ContainerFactory containerFactory;
    public static LayerService layerService;
    public static ArticleFactory articleFactory;

    @BeforeClass
    public static void beforeClass() {
        dimensions3DFactory = new Dimensions3DFactory();
        orientationFactory = new OrientationFactory(dimensions3DFactory);
        orientationService = new OrientationService(orientationFactory);
        containerFactory = new ContainerFactory(dimensions3DFactory, orientationService);
        articleFactory = new ArticleFactory(dimensions3DFactory, orientationService);
        layerService = new LayerService();
    }

    public static Container container(Integer id, Integer w, Integer h, Integer l) {
        return containerFactory.create(id.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static Container container(Integer w, Integer h, Integer l) {
        return containerFactory.create(1L, w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static List<Article> articles(Integer w, Integer h, Integer l, Integer size) {
        ArrayList<Article> articles = new ArrayList<>();
        for (Integer i = 0; i < size; i++) {
            Article article = articleFactory.create(i.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
            articles.add(article);
        }
        return articles;
    }
}
