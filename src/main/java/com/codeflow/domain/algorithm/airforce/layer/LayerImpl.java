package com.codeflow.domain.algorithm.airforce.layer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Unique dimension of the boxes less than then y dimension of the current orientation of the pallet with their
 * individual evaluation values. It is a possible layer thickness value for iterations with the current orientation of
 * the pallet to start the packing.The evaluation value of a dimension represents how close all other boxes are to this
 * layer height if we selected this value as a layer thickness for the packing.
 */
class LayerImpl implements Layer {
    /**
     * Each dimension value in this array represents a different layer thickness value with which each iteration can start packing
     */
    private Double height;

    private Double length;
    /**
     * Evaluation weight value for the corresponding dimension value
     */
    private Double evaluationValue;
    private boolean isDone;
    private boolean isEven;

    private Double layerInLayer;


    LayerImpl(Double height, Double length, Double evaluationValue) {
        this.height = height;
        this.length = length;
        this.evaluationValue = evaluationValue;
        this.isDone = false;
        this.isEven = false;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public Double getEvaluationValue() {
        return evaluationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LayerImpl layer = (LayerImpl) o;

        return new EqualsBuilder()
                .append(height, layer.height)
                .append(length, layer.length)
                .append(evaluationValue, layer.evaluationValue)
                .isEquals();
    }

    @Override
    public void increaseLayerThickness(Double articleHeight) {
        Double heightDiff = articleHeight - this.height;
        layerInLayer = layerInLayer + heightDiff;
        this.height = articleHeight;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(height)
                .append(length)
                .append(evaluationValue)
                .toHashCode();
    }

    @Override
    public void done() {
        isDone = true;
    }

    @Override
    public void even() {
        isEven = true;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "LayerImpl{" +
                "height=" + height +
                ", length=" + length +
                ", evaluationValue=" + evaluationValue +
                '}';
    }
}
