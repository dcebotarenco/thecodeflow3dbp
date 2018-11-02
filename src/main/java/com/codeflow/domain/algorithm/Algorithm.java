package com.codeflow.domain.algorithm;

import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.stock.StockRepository;

public interface Algorithm {

    PackResult run(ContainerType containerType, StockRepository stockRepository);


}
