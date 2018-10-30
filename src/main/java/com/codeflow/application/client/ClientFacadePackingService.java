package com.codeflow.application.client;

import com.codeflow.domain.algorithm.AlgorithmService;
import com.codeflow.domain.algorithm.PackResult;
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
import com.codeflow.domain.iteration.IterationResultRepository;
import com.codeflow.infrastructure.filereader.FileReader;
import com.codeflow.infrastructure.filereader.InputDTOAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ClientFacadePackingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFacadePackingService.class);

    public Result pack(Path path) throws IOException {
        FileReader fileReader = new FileReader(new InputDTOAssembler());
        Input input = fileReader.read(path);
        return pack(input);
    }

    public Result pack(File file) throws IOException {
        return pack(file.toPath());
    }

    public Result pack(Input input) {
        ArticleTypeRepository articleTypeRepository = new ArticleRepositoryImpl();
        ArticleServiceImpl articleService = new ArticleServiceImpl(articleTypeRepository);
        ContainerRepositoryImpl containerRepository = new ContainerRepositoryImpl();
        for (ArticleType articleType : input.getArticleTypes()) {
            articleTypeRepository.saveType(new ArticleTypeImpl(articleType.getWidth(),
                    articleType.getHeight(),
                    articleType.getLength()), articleType.getNumber());
        }
        ContainerType container = new ContainerTypeImpl(input.getContainer().getWidth(),
                input.getContainer().getHeight(),
                input.getContainer().getLength());
        containerRepository.save(container);

        AlgorithmService algorithmService = new AlgorithmService();
        LOGGER.info("Executing with {} and {}", container, articleTypeRepository.receivedArticleTypes());
        PackResult packResult = algorithmService.execute(new AirForceAlgorithm(new LayerServiceImpl(),
                containerRepository, articleService
                , new SearchingServiceImpl(articleService),
                new PackingServiceImpl(articleService), new IterationResultRepository()));
        return new ResultMapper().map(packResult);
    }
}
