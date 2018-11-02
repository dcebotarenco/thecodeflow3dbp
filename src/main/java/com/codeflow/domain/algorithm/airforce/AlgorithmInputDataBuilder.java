package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.stock.Stock;

import java.util.List;

public class AlgorithmInputDataBuilder {
    private ContainerType containerType;
    private List<Stock> stock;

    public AlgorithmInputDataBuilder setContainerType(ContainerType containerType) {
        this.containerType = containerType;
        return this;
    }

    public AlgorithmInputDataBuilder setStock(List<Stock> stock) {
        this.stock = stock;
        return this;
    }

    public AlgorithmInputData createAlgorithmInputData() {
        return new AlgorithmInputData(containerType, stock);
    }
}