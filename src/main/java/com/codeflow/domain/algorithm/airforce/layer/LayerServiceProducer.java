package com.codeflow.domain.algorithm.airforce.layer;

import com.codeflow.domain.serviceproducer.ServiceProducer;

public class LayerServiceProducer implements ServiceProducer<LayerService> {

    LayerFactory<Layer> layerFactory;

    public LayerServiceProducer(LayerFactory<Layer> layerFactory) {
        this.layerFactory = layerFactory;
    }

    @Override
    public LayerService defaultService() {
        return new LayerServiceImpl(layerFactory);
    }
}
