package com.codeflow.domain.algorithm;

import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.stock.StockService;


public class AlgorithmServiceImpl implements AlgorithmService {

    private ContainerRepository containerRepository;
    private StockService stockService;

    public AlgorithmServiceImpl(ContainerRepository containerRepository,
                                StockService stockService) {
        this.containerRepository = containerRepository;
        this.stockService = stockService;
    }


    @Override
    public PackResult execute(Algorithm algorithm) {
        return algorithm.run(containerRepository.container(), stockService);
    }
}
