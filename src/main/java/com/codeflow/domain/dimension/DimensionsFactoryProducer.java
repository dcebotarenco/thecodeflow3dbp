package com.codeflow.domain.dimension;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class DimensionsFactoryProducer implements FactoryProducer<DimensionsFactory> {
    @Override
    public DimensionsFactory defaultFactory() {
        return new DimensionsFactoryImpl();
    }
}
