package com.codeflow.application;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;
import com.codeflow.domain.algorithm.airforce.actions.ActionRepository;
import com.codeflow.domain.algorithm.airforce.actions.ActionRepositoryProducer;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.actions.ActionServiceProducer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceProducer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.packing.PackingServiceProducer;
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
import com.codeflow.domain.article.ArticleFactoryProducer;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.article.ArticleRepositoryProducer;
import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.box.Box3DFactoryProducer;
import com.codeflow.domain.boxtype.*;
import com.codeflow.domain.container.*;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.dimension.DimensionsFactoryProducer;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.gap.GapFactoryProducer;
import com.codeflow.domain.orientation.OrientationFactory;
import com.codeflow.domain.orientation.OrientationFactoryProducer;
import com.codeflow.domain.orientation.OrientationService;
import com.codeflow.domain.orientation.OrientationServiceProducer;
import com.codeflow.infrastructure.filereader.FileReader;
import com.codeflow.infrastructure.filereader.InputDTOAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting..");
        FileReader fileReader = new FileReader(new InputDTOAssembler());
        InputDTO inputDTO = fileReader.read(Paths.get("D:\\personal\\3dbp\\projects\\3d-bin-act-master\\test\\dpp01.txt"));
        LOGGER.info("Received {}", inputDTO);

        DimensionsFactory dimensionsFactory = new DimensionsFactoryProducer().defaultFactory();
        OrientationFactory orientationFactory = new OrientationFactoryProducer(dimensionsFactory).defaultFactory();
        OrientationService orientationService = new OrientationServiceProducer(orientationFactory).defaultService();

        BoxTypeFactory<BoxType> boxTypeBoxTypeFactory = new BoxTypeFactoryProducer().defaultFactory();
        Box3DFactory box3DFactory = new Box3DFactoryProducer(boxTypeBoxTypeFactory, dimensionsFactory, orientationService).defaultFactory();
        ArticleFactory articleFactory = new ArticleFactoryProducer(box3DFactory).defaultFactory();
        LayerService layerService = new LayerServiceProducer().defaultService();
        TopologySituationRepository topologySituationRepository = new TopologySituationRepositoryProvider().defaultRepository();
        CornerFactory<Corner> cornerFactory = new CornerFactoryProducer().defaultFactory();
        TopologyService topologyService = new TopologyServiceProducer(topologySituationRepository, cornerFactory).defaultService();

        ArticleRepository articleRepository = new ArticleRepositoryProducer().defaultRepository();
        BoxTypeRepository boxTypeRepository = new BoxTypeRepositoryProducer(articleRepository).defaultRepository();

        GapFactory gapFactory = new GapFactoryProducer(box3DFactory).defaultFactory();
        SearchingService searchingService = new SearchingServiceProducer(boxTypeRepository).defaultService();
        PackingService packingService = new PackingServiceProducer().defaultService();
        ActionRepository actionRepository = new ActionRepositoryProducer(searchingService, gapFactory, packingService).defaultRepository();

        ActionService actionService = new ActionServiceProducer(actionRepository).defaultService();
        ContainerRepository containerRepository = new ContainerRepositoryProducer().defaultRepository();

        TopViewTopologyFactory topViewTopologyFactory = new TopViewTopologyFactoryProducer().defaultFactory();


        ContainerFactory containerFactory = new ContainerFactoryProducer(box3DFactory).defaultFactory();

        inputDTO.getArticleDTOList().stream().map(dto -> articleFactory.create(dto.getId(),
                dto.getWidth(),
                dto.getLength(),
                dto.getHeight())).forEach(articleRepository::saveReceived);

        Container container = containerFactory.create(inputDTO.getContainerDTO().getId(),
                inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getLength(),
                inputDTO.getContainerDTO().getHeight());
        containerRepository.save(container);

        AlgorithmService algorithmService = new AlgorithmService();
        LOGGER.info("Executing with {} and {}", container, articleRepository.receivedArticles());
        algorithmService.execute(new AirForceAlgorithm(layerService,
                topologyService,
                actionService,
                articleRepository,
                containerRepository,
                cornerFactory, topViewTopologyFactory));


    }
}
