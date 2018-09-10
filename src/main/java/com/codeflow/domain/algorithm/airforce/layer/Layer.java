package com.codeflow.domain.algorithm.airforce.layer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Unique dimension of the boxes less than then y dimension of the current orientation of the pallet with their
 * individual evaluation values. It is a possible layer thickness value for iterations with the current orientation of
 * the pallet to start the packing.The evaluation value of a dimension represents how close all other boxes are to this
 * layer height if we selected this value as a layer thickness for the packing.
 */
public class Layer {
    /**
     * Each dimension value in this array represents a different layer thickness value with which each iteration can start packing
     */
    private Double dimension;
    /**
     * Evaluation weight value for the corresponding dimension value
     */
    private Double evaluationValue;

    Layer(Double dimension, Double evaluationValue) {
        this.dimension = dimension;
        this.evaluationValue = evaluationValue;
    }

    public Double getDimension() {
        return dimension;
    }

    public Double getEvaluationValue() {
        return evaluationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Layer layer = (Layer) o;

        return new EqualsBuilder()
                .append(dimension, layer.dimension)
                .append(evaluationValue, layer.evaluationValue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dimension)
                .append(evaluationValue)
                .toHashCode();
    }
}
