package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Orientation {

    private Dimensions3D dimensions3D;

    Orientation(Dimensions3D dimensions3D) {
        this.dimensions3D = dimensions3D;
    }

    Dimensions3D getDimensions() {
        return dimensions3D;
    }

    public Double getWidth() {
        return getDimensions().getWidth();
    }

    public Double getLength() {
        return getDimensions().getLength();
    }

    public Double getHeight() {
        return getDimensions().getHeight();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Orientation that = (Orientation) o;

        return new EqualsBuilder()
                .append(dimensions3D, that.dimensions3D)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dimensions3D)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Orientation{" +
                "dimensions3D=" + dimensions3D +
                '}';
    }

    public boolean fit(Orientation orientation) {
        return this.getDimensions().getWidth() >= orientation.getDimensions().getWidth() &&
                this.getDimensions().getHeight() >= orientation.getDimensions().getHeight() &&
                this.getDimensions().getLength() >= orientation.getDimensions().getLength();
    }

}
