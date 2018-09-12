package com.codeflow.domain;

import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.boxes.*;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;
import java.util.Random;

public class SharedTest {

    public static Dimensions3DFactory dimensions3DFactory;
    public static OrientationFactory orientationFactory;
    public static OrientationService orientationService;
    public static ContainerFactory containerFactory;
    public static GapFactory gapFactory;
    public static LayerService layerService;
    public static ArticleFactory articleFactory;
    public static ArticleRepository articleRepository;
    public static ContainerRepository containerRepository;
    public static BoxTypeRepository boxTypeRepository;
    public static SearchingService searchingService;

    @Before
    public void clearRepos() {
        articleRepository.clear();
        containerRepository.clear();
    }

    @BeforeClass
    public static void beforeClass() {
        dimensions3DFactory = new Dimensions3DFactory();
        orientationFactory = new OrientationFactory(dimensions3DFactory);
        orientationService = new OrientationService(orientationFactory);
        containerFactory = new ContainerFactory(dimensions3DFactory, orientationService);
        articleFactory = new ArticleFactory(dimensions3DFactory, orientationService);
        gapFactory = new GapFactory(dimensions3DFactory, orientationService);
        containerRepository = new ContainerRepository();
        layerService = new LayerService();
        articleRepository = new ArticleRepository();
        boxTypeRepository = new BoxTypeRepository(articleRepository);
        searchingService = new SearchingService(boxTypeRepository);
    }

    public static Container container(Integer id, Integer w, Integer h, Integer l) {
        Container container = containerFactory.create(id.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
        containerRepository.save(container);
        return containerRepository.container();
    }

    public static Container container(Integer w, Integer h, Integer l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        Container container = containerFactory.create(id, w.doubleValue(), h.doubleValue(), l.doubleValue());
        containerRepository.save(container);
        return containerRepository.container();

    }

    public static List<Article> articles(Integer w, Integer h, Integer l, Integer number) {
        for (Integer i = 0; i < number; i++) {
            Article article = articleFactory.create(i.longValue(), w.doubleValue(), h.doubleValue(), l.doubleValue());
            articleRepository.saveReceived(article);
        }
        return articleRepository.receivedArticles();
    }

    public static Gap gap(Integer w, Integer h, Integer l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        return gapFactory.create(id, w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static Gap gap(Double w, Double h, Double l) {
        Random r = new Random();
        int random = r.nextInt(100 + 1);
        Long id = (long) random;
        return gapFactory.create(id, w, h, l);
    }
}
