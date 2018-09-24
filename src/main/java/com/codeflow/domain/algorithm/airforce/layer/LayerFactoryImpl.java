package com.codeflow.domain.algorithm.airforce.layer;

class LayerFactoryImpl implements LayerFactory<Layer> {
    @Override
    public Layer create(Double height, Double length, Double evaluationValue) {
        return new LayerImpl(height, length, evaluationValue);
    }
}
