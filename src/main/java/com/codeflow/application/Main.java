package com.codeflow.application;

import com.codeflow.application.configuration.DefaultConfiguration;
import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;
import com.codeflow.domain.container.Container;
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
        DefaultConfiguration config = new DefaultConfiguration();
        inputDTO.getArticleDTOList().stream().map(dto -> config.getArticleFactory().create(dto.getId(),
                dto.getWidth(),
                dto.getLength(),
                dto.getHeight())).forEach(config.getArticleRepository()::saveReceived);

        Container container = config.getContainerFactory().create(inputDTO.getContainerDTO().getId(),
                inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getLength(),
                inputDTO.getContainerDTO().getHeight());
        config.getContainerRepository().save(container);

        AlgorithmService algorithmService = new AlgorithmService();
        LOGGER.info("Executing with {} and {}", container, config.getArticleRepository().receivedArticles());
        algorithmService.execute(new AirForceAlgorithm(config.getLayerService(),
                config.getTopologyService(),
                config.getActionService(),
                config.getArticleRepository(),
                config.getContainerRepository(),
                config.getArticleService()));


    }

}
