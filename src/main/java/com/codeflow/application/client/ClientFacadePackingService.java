package com.codeflow.application.client;

import com.codeflow.application.client.container.ContainerService;
import com.codeflow.application.client.container.ContainerServiceImpl;
import com.codeflow.application.client.stock.Stock;
import com.codeflow.application.client.stock.StockService;
import com.codeflow.application.client.stock.StockServiceImpl;
import com.codeflow.domain.algorithm.*;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceImpl;
import com.codeflow.domain.articletype.ArticleRepositoryImpl;
import com.codeflow.domain.articletype.ArticleTypeRepository;
import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.ContainerRepositoryImpl;
import com.codeflow.domain.stock.StockRepository;
import com.codeflow.domain.stock.StockRepositoryImpl;
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
    private StockRepository stockRepository;
    private StockService stockService;
    private com.codeflow.domain.stock.StockService domainStockService;
    private ContainerService containerService;
    private AlgorithmService algorithmService;
    private AlgorithmRepository<Algorithm> algorithmRepository;

    public ClientFacadePackingService() {
        articleTypeRepository = new ArticleRepositoryImpl();
        stockRepository = new StockRepositoryImpl();
        containerRepository = new ContainerRepositoryImpl();
        domainStockService = new com.codeflow.domain.stock.StockServiceImpl(stockRepository, new LayerServiceImpl());
        stockService = new StockServiceImpl(domainStockService, articleTypeRepository);
        containerService = new ContainerServiceImpl(containerRepository);
        algorithmService = new AlgorithmServiceImpl(containerRepository, domainStockService);
        algorithmRepository = new AlgorithmRepositoryImpl();
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
        for (Stock stock : input.getStock()) {
            stockService.create(stock);
        }
        containerService.create(input.getContainer());
        Algorithm algorithm = algorithmRepository.getAirForceAlgorithm();
        PackResult packResult = algorithmService.execute(algorithm);
        return new ResultMapper().map(packResult);
    }
}
