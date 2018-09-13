package com.codeflow.domain.dimension;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

class DimensionsImpl implements Dimensions {

    private Map<Dimension, Double> dimensionDoubleMap;

    DimensionsImpl(Map<Dimension, Double> dimensionDoubleMap) {
        this.dimensionDoubleMap = dimensionDoubleMap;
    }

    @Override
    public Double getWidth() {
        return dimensionDoubleMap.get(Dimension.WIDTH);
    }

    @Override
    public Double getLength() {
        return dimensionDoubleMap.get(Dimension.LENGTH);
    }

    @Override
    public Double getHeight() {
        return dimensionDoubleMap.get(Dimension.HEIGHT);
    }

    @Override
    public Double getVolume() {
        return getWidth() * getHeight() * getLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DimensionsImpl that = (DimensionsImpl) o;

        return new EqualsBuilder()
                .append(dimensionDoubleMap, that.dimensionDoubleMap)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dimensionDoubleMap)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Dimensions3D{" +
                "dimensionDoubleMap=" + dimensionDoubleMap +
                '}';
    }
}
