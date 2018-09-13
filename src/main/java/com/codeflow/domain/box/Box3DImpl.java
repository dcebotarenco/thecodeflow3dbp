package com.codeflow.domain.box;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * A Box has 3D dimensions.It also has Orientations based on its dimensions
 * Obviously, if a box has three identical dimensions, it has only one orientation.
 * In general, we have 1,2 or 6 orientations for 1,2 or 3 unique dimensions, respectively.
 */
class Box3DImpl implements Box3D {

    private Long id;
    private BoxType boxType;

    Box3DImpl(Long id, BoxType boxType) {
        this.id = id;
        this.boxType = boxType;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Dimensions getDimensions() {
        return boxType.getDimensions();
    }

    @Override
    public Double getWidth() {
        return boxType.getDimensions().getWidth();
    }

    @Override
    public Double getLength() {
        return boxType.getDimensions().getLength();
    }

    @Override
    public Double getHeight() {
        return boxType.getDimensions().getHeight();
    }

    @Override
    public List<Orientation> getOrientations() {
        return boxType.getOrientations();
    }

    @Override
    public BoxType getBoxType() {
        return boxType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Box3DImpl box3DImpl = (Box3DImpl) o;

        return new EqualsBuilder()
                .append(id, box3DImpl.id)
                .append(boxType, box3DImpl.boxType)
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
