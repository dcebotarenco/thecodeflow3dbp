package com.codeflow.domain;

import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceProducer;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceProducer;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactoryProducer;
import com.codeflow.domain.article.*;
import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.box.Box3DFactoryProducer;
import com.codeflow.domain.boxtype.*;
import com.codeflow.domain.container.*;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.dimension.DimensionsFactoryProducer;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.gap.GapFactoryProducer;
import com.codeflow.domain.orientation.*;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.List;
import java.util.Random;

public class SharedTest {

    protected static DimensionsFactory dimensionsFactory;
    protected static OrientationFactory orientationFactory;
    protected static OrientationService<Orientation> orientationService;
    protected static ContainerFactory containerFactory;
    protected static GapFactory gapFactory;
    protected static LayerService layerService;
    protected static ArticleFactory articleFactory;
    protected static ArticleRepository articleRepository;
    protected static ContainerRepository containerRepository;
    protected static BoxTypeRepository boxTypeRepository;
    protected static SearchingService searchingService;
    protected static CornerFactory cornerFactory;

    @Before
    public void clearRepos() {
        articleRepository.clear();
        containerRepository.clear();
    }

    @BeforeClass
    public static void beforeClass() {
        BoxTypeFactory<BoxType> boxTypeBoxTypeFactory = new BoxTypeFactoryProducer().defaultFactory();
        dimensionsFactory = new DimensionsFactoryProducer().defaultFactory();
        orientationFactory = new OrientationFactoryProducer(dimensionsFactory).defaultFactory();
        orientationService = new OrientationServiceProducer(orientationFactory).defaultService();
        Box3DFactory box3DFactory = new Box3DFactoryProducer(boxTypeBoxTypeFactory, dimensionsFactory, orientationService).defaultFactory();
        containerFactory = new ContainerFactoryProducer(box3DFactory).defaultFactory();
        articleFactory = new ArticleFactoryProducer(box3DFactory).defaultFactory();
        gapFactory = new GapFactoryProducer(box3DFactory).defaultFactory();
        containerRepository = new ContainerRepositoryProducer().defaultRepository();
        layerService = new LayerServiceProducer().defaultService();
        articleRepository = new ArticleRepositoryProducer().defaultRepository();
        boxTypeRepository = new BoxTypeRepositoryProducer(articleRepository).defaultRepository();
        searchingService = new SearchingServiceProducer(boxTypeRepository).defaultService();
        cornerFactory = new CornerFactoryProducer().defaultFactory();
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
