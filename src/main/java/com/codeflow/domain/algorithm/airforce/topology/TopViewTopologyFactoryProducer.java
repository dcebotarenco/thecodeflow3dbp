package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class TopViewTopologyFactoryProducer implements FactoryProducer<TopViewTopologyFactory> {
    @Override
    public TopViewTopologyFactory defaultFactory() {
        return new TopViewTopologyFactoryImpl();
    }
}
