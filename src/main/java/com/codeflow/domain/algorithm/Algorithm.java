package com.codeflow.domain.algorithm;

import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.stock.StockService;

public interface Algorithm {

    PackResult run(ContainerType containerType, StockService stockService);


}
