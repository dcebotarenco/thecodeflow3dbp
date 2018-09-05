package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Box3D {

    private Long id;
    private final Dimensions3D dimensions;

    public Box3D(Long id, Dimensions3D dimensions3D) {
        this.id = id;
        this.dimensions = dimensions3D;
    }


    public Long getId() {
        return id;
    }

    public Dimensions3D getDimensions() {
        return dimensions;
    }


    public Double getWidth() {
        return dimensions.getWidth();
    }

    public Double getLength() {
        return dimensions.getLength();
    }

    public Double getHeight() {
        return dimensions.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Box3D box3D = (Box3D) o;

        return new EqualsBuilder()
                .append(id, box3D.id)
                .append(dimensions, box3D.dimensions)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(dimensions)
                .toHashCode();
    }
}
