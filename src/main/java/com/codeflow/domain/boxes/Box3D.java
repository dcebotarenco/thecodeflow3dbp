package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Set;

/**
 * A Box has 3D dimensions.It also has Orientations based on its dimensions
 * Obviously, if a box has three identical dimensions, it has only one orientation.
 * In general, we have 1,2 or 6 orientations for 1,2 or 3 unique dimensions, respectively.
 */
public abstract class Box3D {

    private Long id;
    private final Dimensions3D dimensions;
    private final List<Orientation> orientations;

    Box3D(Long id, Dimensions3D dimensions3D, List<Orientation> orientations) {
        this.id = id;
        this.dimensions = dimensions3D;
        this.orientations = orientations;
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

    public List<Orientation> getOrientations() {
        return orientations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Box3D box3D = (Box3D) o;

        return new EqualsBuilder()
                .append(id, box3D.id)
                .append(dimensions, box3D.dimensions)
                .append(orientations, box3D.orientations)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(dimensions)
                .append(orientations)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Box3D{" +
                "id=" + id +
                ", dimensions=" + dimensions +
                ", orientations=" + orientations +
                '}';
    }
}
