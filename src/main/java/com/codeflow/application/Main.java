package com.codeflow.application;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.packing.ActionRepository;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepository;
import com.codeflow.domain.boxes.*;
import com.codeflow.infrastructure.filereader.FileReader;
import com.codeflow.infrastructure.filereader.InputDTOAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting..");
        FileReader fileReader = new FileReader(new InputDTOAssembler());
        InputDTO inputDTO = fileReader.read(Paths.get("D:\\personal\\3dbp\\projects\\3d-bin-pack-master\\test\\dpp01.txt"));
        LOGGER.info("Received {}", inputDTO);
        Dimensions3DFactory dimensions3DFactory = new Dimensions3DFactory();
        LayerService layerService = new LayerService();
        OrientationService orientationService = new OrientationService(new OrientationFactory(dimensions3DFactory));
        ArticleFactory articleFactory = new ArticleFactory(dimensions3DFactory, orientationService);
        TopologyService topologyService = new TopologyService(new TopologySituationRepository());
        PackingService packingService = new PackingService(new ActionRepository());

        ContainerFactory containerFactory = new ContainerFactory(dimensions3DFactory, orientationService);

        List<Article> articles = inputDTO.getArticleDTOList().stream().map(dto -> articleFactory.create(dto.getId(),
                dto.getWidth(),
                dto.getLength(),
                dto.getHeight())).collect(Collectors.toList());
        Container container = containerFactory.create(inputDTO.getContainerDTO().getId(),
                inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getLength(),
                inputDTO.getContainerDTO().getHeight());

        AlgorithmService algorithmService = new AlgorithmService();
        LOGGER.info("Executing with {} and {}", container, articles);
        algorithmService.execute(new AirForceAlgorithm(layerService, topologyService, packingService), container, articles);


    }
}
