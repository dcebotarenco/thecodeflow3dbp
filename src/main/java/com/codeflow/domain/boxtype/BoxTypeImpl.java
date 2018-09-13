package com.codeflow.domain.boxtype;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

class BoxTypeImpl implements BoxType<Orientation> {

    private final Dimensions dimensions;
    private final List<Orientation> orientations;

    BoxTypeImpl(Dimensions dimensions, List<Orientation> orientations) {
        this.dimensions = dimensions;
        this.orientations = orientations;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public List<Orientation> getOrientations() {
        return orientations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoxTypeImpl boxType = (BoxTypeImpl) o;

        return new EqualsBuilder()
                .append(dimensions, boxType.dimensions)
                .append(orientations, boxType.orientations)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dimensions)
                .append(orientations)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoxType{" +
                "dimensions=" + dimensions +
                ", orientations=" + orientations +
                '}';
    }
}
