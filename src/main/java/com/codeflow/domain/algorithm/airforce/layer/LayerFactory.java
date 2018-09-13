package com.codeflow.domain.algorithm.airforce.layer;

public interface LayerFactory<L extends Layer> {

    L create(Double dimension, Double evaluationValue);
}
