package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class LayerFactoryProducer implements FactoryProducer<LayerFactory> {
    @Override
    public LayerFactory defaultFactory() {
        return new LayerFactoryImpl();
    }
}
