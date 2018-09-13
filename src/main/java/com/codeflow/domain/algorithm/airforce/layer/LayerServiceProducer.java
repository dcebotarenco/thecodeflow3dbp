package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.serviceproducer.ServiceProducer;

public class LayerServiceProducer implements ServiceProducer<LayerService> {
    @Override
    public LayerService defaultService() {
        return new LayerServiceImpl();
    }
}
