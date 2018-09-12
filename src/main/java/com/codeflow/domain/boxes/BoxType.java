package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class BoxType {

    private final Dimensions3D dimensions;
    private final List<Orientation> orientations;

    public BoxType(Dimensions3D dimensions, List<Orientation> orientations) {
        this.dimensions = dimensions;
        this.orientations = orientations;
    }

    public Dimensions3D getDimensions() {
        return dimensions;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoxType boxType = (BoxType) o;

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
