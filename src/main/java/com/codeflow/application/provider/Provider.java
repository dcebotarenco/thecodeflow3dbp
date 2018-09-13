package com.codeflow.application.provider;

import com.codeflow.domain.algorithm.airforce.actions.ActionRepository;
import com.codeflow.domain.algorithm.airforce.actions.ActionRepositoryProducer;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.actions.ActionServiceProducer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.packing.PackingServiceProducer;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceProducer;
import com.codeflow.domain.article.ArticleFactory;
import com.codeflow.domain.article.ArticleFactoryProducer;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.article.orientation.ArticleOrientationFactory;
import com.codeflow.domain.article.orientation.ArticleOrientationFactoryProducer;
import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.box.Box3DFactoryProducer;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeFactory;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.boxtype.BoxTypeRepositoryProducer;
import com.codeflow.domain.container.ContainerFactory;
import com.codeflow.domain.container.ContainerFactoryProducer;
import com.codeflow.domain.container.orientation.ContainerOrientationFactory;
import com.codeflow.domain.container.orientation.ContainerOrientationFactoryProducer;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.gap.GapFactoryProducer;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;
import com.codeflow.domain.orientation.OrientationService;
import com.codeflow.domain.orientation.OrientationServiceProducer;

public class Provider {

    public static ContainerFactory createContainerFactory(OrientationFactory<Orientation> orientationFactory,
                                                          BoxTypeFactory<BoxType> boxTypeBoxTypeFactory,
                                                          DimensionsFactory dimensionsFactory) {

        ContainerOrientationFactory containerOrientationFactory = new ContainerOrientationFactoryProducer(orientationFactory).defaultFactory();
        OrientationService containerOrientationService = new OrientationServiceProducer(containerOrientationFactory).defaultService();
        Box3DFactory containerBox3DFactory = new Box3DFactoryProducer(boxTypeBoxTypeFactory, dimensionsFactory, containerOrientationService).defaultFactory();
        return new ContainerFactoryProducer(containerBox3DFactory).defaultFactory();

    }

    public static ArticleFactory createArticleFactory(OrientationFactory<Orientation> orientationFactory,
                                                      BoxTypeFactory<BoxType> boxTypeBoxTypeFactory,
                                                      DimensionsFactory dimensionsFactory) {
        ArticleOrientationFactory articleOrientationFactory = new ArticleOrientationFactoryProducer(orientationFactory).defaultFactory();
        OrientationService articleOrientationService = new OrientationServiceProducer(articleOrientationFactory).defaultService();
        Box3DFactory articleBox3DFactory = new Box3DFactoryProducer(boxTypeBoxTypeFactory, dimensionsFactory, articleOrientationService).defaultFactory();
        return new ArticleFactoryProducer(articleBox3DFactory).defaultFactory();
    }

    public static GapFactory createGapFactory(OrientationFactory<Orientation> orientationFactory,
                                              BoxTypeFactory<BoxType> boxTypeBoxTypeFactory,
                                              DimensionsFactory dimensionsFactory) {
        OrientationService orientationService = new OrientationServiceProducer(orientationFactory).defaultService();
        Box3DFactory box3DFactory = new Box3DFactoryProducer(boxTypeBoxTypeFactory, dimensionsFactory, orientationService).defaultFactory();
        return new GapFactoryProducer(box3DFactory).defaultFactory();
    }

    public static ActionService createActionService(OrientationFactory<Orientation> orientationFactory,
                                                    BoxTypeFactory<BoxType> boxTypeBoxTypeFactory,
                                                    DimensionsFactory dimensionsFactory, ArticleRepository articleRepository) {
        BoxTypeRepository boxTypeRepository = new BoxTypeRepositoryProducer(articleRepository).defaultRepository();
        GapFactory gapFactory = Provider.createGapFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
        SearchingService searchingService = new SearchingServiceProducer(boxTypeRepository).defaultService();
        PackingService packingService = new PackingServiceProducer().defaultService();
        ActionRepository actionRepository = new ActionRepositoryProducer(searchingService, gapFactory, packingService).defaultRepository();

        return new ActionServiceProducer(actionRepository).defaultService();
    }
}
