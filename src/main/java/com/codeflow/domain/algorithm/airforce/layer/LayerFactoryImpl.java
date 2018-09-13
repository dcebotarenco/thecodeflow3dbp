package com.codeflow.domain.algorithm.airforce.layer;

class LayerFactoryImpl implements LayerFactory<Layer> {
    @Override
    public Layer create(Double dimension, Double evaluationValue) {
        return new LayerImpl(dimension, evaluationValue);
    }
}
