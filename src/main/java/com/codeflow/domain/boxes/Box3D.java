package com.codeflow.domain.boxes;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * A Box has 3D dimensions.It also has Orientations based on its dimensions
 * Obviously, if a box has three identical dimensions, it has only one orientation.
 * In general, we have 1,2 or 6 orientations for 1,2 or 3 unique dimensions, respectively.
 */
public abstract class Box3D {

    private Long id;
    private BoxType boxType;

    public Box3D(Long id, BoxType boxType) {
        this.id = id;
        this.boxType = boxType;
    }

    public Long getId() {
        return id;
    }

    public Dimensions3D getDimensions() {
        return boxType.getDimensions();
    }

    public Double getWidth() {
        return boxType.getDimensions().getWidth();
    }

    public Double getLength() {
        return boxType.getDimensions().getLength();
    }

    public Double getHeight() {
        return boxType.getDimensions().getHeight();
    }

    public List<Orientation> getOrientations() {
        return boxType.getOrientations();
    }

    public BoxType getBoxType() {
        return boxType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Box3D box3D = (Box3D) o;

        return new EqualsBuilder()
                .append(id, box3D.id)
                .append(boxType, box3D.boxType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(boxType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Box3D{" +
                "id=" + id +
                ", boxType=" + boxType +
                '}';
    }
}
