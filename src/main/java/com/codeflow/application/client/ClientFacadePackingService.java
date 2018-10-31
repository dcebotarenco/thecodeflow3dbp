package com.codeflow.application.client;

import com.codeflow.application.articletype.ArticleService;
import com.codeflow.application.articletype.ArticleServiceImpl;
import com.codeflow.application.containertype.ContainerService;
import com.codeflow.application.containertype.ContainerServiceImpl;
import com.codeflow.domain.algorithm.*;
import com.codeflow.domain.articletype.ArticleRepositoryImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;
import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.ContainerRepositoryImpl;
import com.codeflow.infrastructure.filereader.FileReader;
import com.codeflow.infrastructure.filereader.InputDTOAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ClientFacadePackingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFacadePackingService.class);

    private ContainerRepository containerRepository;
    private ArticleTypeRepository articleTypeRepository;
    private ArticleService articleService;
    private ContainerService containerService;
    private AlgorithmService algorithmService;
    private AlgorithmRepository<Algorithm> algorithmRepository;

    public ClientFacadePackingService() {
        articleTypeRepository = new ArticleRepositoryImpl();
        containerRepository = new ContainerRepositoryImpl();
        articleService = new ArticleServiceImpl(articleTypeRepository);
        containerService = new ContainerServiceImpl(containerRepository);
        algorithmService = new AlgorithmServiceImpl(containerRepository, articleTypeRepository);
        algorithmRepository = new AlgorithmRepositoryImpl();
    }

    public ClientFacadePackingService(ArticleService articleService, ContainerService containerService) {
        this.articleService = articleService;
        this.containerService = containerService;
    }


    public Result pack(Path path) throws IOException {
        FileReader fileReader = new FileReader(new InputDTOAssembler());
        Input input = fileReader.read(path);
        return pack(input);
    }

    public Result pack(File file) throws IOException {
        return pack(file.toPath());
    }

    public Result pack(Input input) {
        for (ArticleType articleType : input.getArticleTypes()) {
            articleService.create(articleType);
        }
        containerService.create(input.getContainer());
        Algorithm algorithm = algorithmRepository.getAirForceAlgorithm();
        PackResult packResult = algorithmService.execute(algorithm);
        return new ResultMapper().map(packResult);
    }
}
