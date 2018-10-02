package com.codeflow.domain.algorithm.airforce.layer;

public interface Layer {

    Double getLength();

    Double getHeight();

    Double getEvaluationValue();

    void done();

    void even();

    boolean isDone();

    void increaseLayerThickness(Double articleHeight);
}
