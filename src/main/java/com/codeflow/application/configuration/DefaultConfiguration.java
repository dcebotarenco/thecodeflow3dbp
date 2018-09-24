package com.codeflow.application.configuration;

import com.codeflow.application.provider.Provider;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.*;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceProducer;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyFactory;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyFactoryProducer;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyServiceProducer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactoryProducer;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepository;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepositoryProvider;
import com.codeflow.domain.article.*;
import com.codeflow.domain.boxtype.*;
import com.codeflow.domain.container.ContainerFactory;
import com.codeflow.domain.container.ContainerRepository;
import com.codeflow.domain.container.ContainerRepositoryProducer;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.dimension.DimensionsFactoryProducer;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.orientation.*;
import com.codeflow.domain.position.PositionFactory;
import com.codeflow.domain.position.PositionFactoryProducer;

public class DefaultConfiguration {


    private DimensionsFactory dimensionsFactory;
    private OrientationFactory<Orientation> orientationFactory;
    private BoxTypeFactory<BoxType> boxTypeBoxTypeFactory;
    private BoxTypeRepository boxTypeRepository;
    private ArticleFactory articleFactory;
    private LayerFactory<Layer> layerFactory;
    private LayerService layerService;
    private TopologySituationRepository topologySituationRepository;
    private CornerFactory<Corner> cornerFactory;
    private TopologyService topologyService;
    private ArticleRepository articleRepository;
    private ActionService actionService;
    private ContainerRepository containerRepository;
    private TopViewTopologyFactory topViewTopologyFactory;
    private ContainerFactory containerFactory;
    private GapFactory gapFactory;
    private SearchingService searchingService;
    private OrientationService orientationService;
    private ArticleService articleService;
    private PositionFactory positionFactory;

    public DefaultConfiguration() {
        dimensionsFactory = new DimensionsFactoryProducer().defaultFactory();
        orientationFactory = new OrientationFactoryProducer(dimensionsFactory).defaultFactory();
        boxTypeBoxTypeFactory = new BoxTypeFactoryProducer().defaultFactory();
        articleFactory = Provider.createArticleFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
        layerFactory = new LayerFactoryProducer().defaultFactory();
        layerService = new LayerServiceProducer(layerFactory).defaultService();
        topologySituationRepository = new TopologySituationRepositoryProvider().defaultRepository();
        cornerFactory = new CornerFactoryProducer().defaultFactory();
        topologyService = new TopologyServiceProducer(topologySituationRepository, cornerFactory).defaultService();
        articleRepository = new ArticleRepositoryProducer().defaultRepository();
        articleService = new ArticleServiceProducer(articleRepository).defaultService();
        containerFactory = Provider.createContainerFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
        positionFactory = new PositionFactoryProducer().defaultFactory();
        actionService = Provider.createActionService(orientationFactory,
                boxTypeBoxTypeFactory,
                dimensionsFactory,
                articleRepository,
                articleService, positionFactory, topologyService, cornerFactory, layerFactory);
        containerRepository = new ContainerRepositoryProducer().defaultRepository();
        topViewTopologyFactory = new TopViewTopologyFactoryProducer().defaultFactory();
        gapFactory = Provider.createGapFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
        boxTypeRepository = new BoxTypeRepositoryProducer(articleRepository).defaultRepository();
        searchingService = new SearchingServiceProducer(boxTypeRepository).defaultService();
        orientationService = new OrientationServiceProducer(orientationFactory).defaultService();
    }

    public DimensionsFactory getDimensionsFactory() {
        return dimensionsFactory;
    }

    public OrientationFactory<Orientation> getOrientationFactory() {
        return orientationFactory;
    }

    public BoxTypeFactory<BoxType> getBoxTypeBoxTypeFactory() {
        return boxTypeBoxTypeFactory;
    }

    public ArticleFactory getArticleFactory() {
        return articleFactory;
    }

    public LayerService getLayerService() {
        return layerService;
    }

    public TopologySituationRepository getTopologySituationRepository() {
        return topologySituationRepository;
    }

    public CornerFactory<Corner> getCornerFactory() {
        return cornerFactory;
    }

    public TopologyService getTopologyService() {
        return topologyService;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public ContainerRepository getContainerRepository() {
        return containerRepository;
    }

    public TopViewTopologyFactory getTopViewTopologyFactory() {
        return topViewTopologyFactory;
    }

    public ContainerFactory getContainerFactory() {
        return containerFactory;
    }

    public GapFactory getGapFactory() {
        return gapFactory;
    }

    public BoxTypeRepository getBoxTypeRepository() {
        return boxTypeRepository;
    }

    public SearchingService getSearchingService() {
        return searchingService;
    }

    public OrientationService getOrientationService() {
        return orientationService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public PositionFactory getPositionFactory() {
        return positionFactory;
    }
}
