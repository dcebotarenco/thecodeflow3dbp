package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

public class Dimensions3D {

    private Map<Dimension, Double> dimensionDoubleMap;

    Dimensions3D(Map<Dimension, Double> dimensionDoubleMap) {
        this.dimensionDoubleMap = dimensionDoubleMap;
    }


    Double getWidth() {
        return dimensionDoubleMap.get(Dimension.WIDTH);
    }

    Double getLength() {
        return dimensionDoubleMap.get(Dimension.LENGTH);
    }

    Double getHeight() {
        return dimensionDoubleMap.get(Dimension.HEIGHT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Dimensions3D that = (Dimensions3D) o;

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
}
