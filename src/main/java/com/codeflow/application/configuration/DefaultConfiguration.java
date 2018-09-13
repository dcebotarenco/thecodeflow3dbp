package com.codeflow.application.configuration;

import com.codeflow.application.provider.Provider;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceProducer;
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
import com.codeflow.domain.article.ArticleFactory;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.article.ArticleRepositoryProducer;
import com.codeflow.domain.boxtype.*;
import com.codeflow.domain.container.ContainerFactory;
import com.codeflow.domain.container.ContainerRepository;
import com.codeflow.domain.container.ContainerRepositoryProducer;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.dimension.DimensionsFactoryProducer;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.orientation.*;

public class DefaultConfiguration {


    DimensionsFactory dimensionsFactory;
    OrientationFactory<Orientation> orientationFactory;
    BoxTypeFactory<BoxType> boxTypeBoxTypeFactory;
    BoxTypeRepository boxTypeRepository;
    ArticleFactory articleFactory;
    LayerService layerService;
    TopologySituationRepository topologySituationRepository;
    CornerFactory<Corner> cornerFactory;
    TopologyService topologyService;
    ArticleRepository articleRepository;
    ActionService actionService;
    ContainerRepository containerRepository;
    TopViewTopologyFactory topViewTopologyFactory;
    ContainerFactory containerFactory;
    GapFactory gapFactory;
    SearchingService searchingService;
    OrientationService orientationService;

    public DefaultConfiguration() {
        dimensionsFactory = new DimensionsFactoryProducer().defaultFactory();
        orientationFactory = new OrientationFactoryProducer(dimensionsFactory).defaultFactory();
        boxTypeBoxTypeFactory = new BoxTypeFactoryProducer().defaultFactory();
        articleFactory = Provider.createArticleFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
        layerService = new LayerServiceProducer().defaultService();
        topologySituationRepository = new TopologySituationRepositoryProvider().defaultRepository();
        cornerFactory = new CornerFactoryProducer().defaultFactory();
        topologyService = new TopologyServiceProducer(topologySituationRepository, cornerFactory).defaultService();
        articleRepository = new ArticleRepositoryProducer().defaultRepository();
        actionService = Provider.createActionService(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory, articleRepository);
        containerRepository = new ContainerRepositoryProducer().defaultRepository();
        topViewTopologyFactory = new TopViewTopologyFactoryProducer().defaultFactory();
        containerFactory = Provider.createContainerFactory(orientationFactory, boxTypeBoxTypeFactory, dimensionsFactory);
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
}
