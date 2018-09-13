package com.codeflow.domain.algorithm.airforce.topology.corner;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class CornerFactoryProducer implements FactoryProducer<CornerFactory> {
    @Override
    public CornerFactory<Corner> defaultFactory() {
        return new CornerFactoryImpl();
    }
}
