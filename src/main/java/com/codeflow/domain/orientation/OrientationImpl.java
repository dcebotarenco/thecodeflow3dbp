package com.codeflow.domain.orientation;

import com.codeflow.domain.dimension.Dimensions;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class OrientationImpl implements Orientation {

    private Dimensions dimensions;

    OrientationImpl(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public Double getWidth() {
        return getDimensions().getWidth();
    }

    @Override
    public Double getLength() {
        return getDimensions().getLength();
    }

    @Override
    public Double getHeight() {
        return getDimensions().getHeight();
    }

    @Override
    public Double getVolume() {
        return getDimensions().getVolume();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrientationImpl that = (OrientationImpl) o;

        return new EqualsBuilder()
                .append(dimensions, that.dimensions)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dimensions)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Orientation{" +
                "dimensions3D=" + dimensions +
                '}';
    }

    public boolean fit(Orientation orientation) {
        return this.getDimensions().getWidth() >= orientation.getDimensions().getWidth() &&
                this.getDimensions().getHeight() >= orientation.getDimensions().getHeight() &&
                this.getDimensions().getLength() >= orientation.getDimensions().getLength();
    }

}
