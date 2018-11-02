package com.codeflow.domain.algorithm;

import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.stock.StockRepository;


public class AlgorithmServiceImpl implements AlgorithmService {

    private ContainerRepository containerRepository;
    private StockRepository stockRepository;

    public AlgorithmServiceImpl(ContainerRepository containerRepository,
                                StockRepository stockRepository) {
        this.containerRepository = containerRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public PackResult execute(Algorithm algorithm) {
        return algorithm.run(containerRepository.container(), stockRepository);
    }
}
