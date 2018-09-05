package com.codeflow.domain.orientation;

import com.codeflow.domain.boxes.Dimensions3D;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Orientation {
    Dimensions3D dimensions3D;

    Orientation(Dimensions3D dimensions3D) {
        this.dimensions3D = dimensions3D;
    }

    public Dimensions3D getDimensions3D() {
        return dimensions3D;
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
}
