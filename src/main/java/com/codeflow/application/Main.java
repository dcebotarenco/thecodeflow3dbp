package com.codeflow.application;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceImpl;
import com.codeflow.domain.algorithm.airforce.packing.PackingServiceImpl;
import com.codeflow.domain.algorithm.airforce.searching.SearchingServiceImpl;
import com.codeflow.domain.articletype.ArticleRepositoryImpl;
import com.codeflow.domain.articletype.ArticleServiceImpl;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;
import com.codeflow.domain.containertype.ContainerRepositoryImpl;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.ContainerTypeImpl;
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
        InputDTO inputDTO = fileReader.read(Paths.get("./src/test/resources/input/dpp02.txt"));
        LOGGER.info("Received {}", inputDTO);
        ArticleTypeRepository articleTypeRepository = new ArticleRepositoryImpl();
        ArticleServiceImpl articleService = new ArticleServiceImpl(articleTypeRepository);
        ContainerRepositoryImpl containerRepository = new ContainerRepositoryImpl();

        for (ArticleTypeDTO articleTypeDTO : inputDTO.getArticleTypeDTOList()) {
            articleTypeRepository.saveType(new ArticleTypeImpl(articleTypeDTO.getWidth(),
                    articleTypeDTO.getHeight(),
                    articleTypeDTO.getLength()), articleTypeDTO.getNumber());
        }

        ContainerType container = new ContainerTypeImpl(inputDTO.getContainerDTO().getWidth(),
                inputDTO.getContainerDTO().getHeight(),
                inputDTO.getContainerDTO().getLength());
        containerRepository.save(container);

        AlgorithmService algorithmService = new AlgorithmService();
        LOGGER.info("Executing with {} and {}", container, articleTypeRepository.receivedArticleTypes());
        algorithmService.execute(new AirForceAlgorithm(new LayerServiceImpl(),
                containerRepository, articleService
                , new SearchingServiceImpl(articleService), new PackingServiceImpl(articleService)));
    }

}
