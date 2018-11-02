package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.stock.Stock;

import java.util.List;

public class AlgorithmInputData {

    private ContainerType containerType;
    private List<Stock> stock;

    AlgorithmInputData(ContainerType containerType, List<Stock> stock) {
        this.containerType = containerType;
        this.stock = stock;
    }

    public List<Stock> getStock() {
        return stock;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

}
