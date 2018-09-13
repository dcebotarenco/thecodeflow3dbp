package com.codeflow.domain.container;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Container in what the Articles have to be packed.
 */
class ContainerImpl implements Container {

    private Box3D box3D;

    ContainerImpl(Box3D box3D) {
        this.box3D = box3D;
    }

    @Override
    public Long getId() {
        return box3D.getId();
    }

    @Override
    public Dimensions getDimensions() {
        return box3D.getDimensions();
    }

    @Override
    public Double getWidth() {
        return box3D.getWidth();
    }

    @Override
    public Double getLength() {
        return box3D.getLength();
    }

    @Override
    public Double getHeight() {
        return box3D.getHeight();
    }

    @Override
    public List<Orientation> getOrientations() {
        return box3D.getOrientations();
    }

    @Override
    public BoxType getBoxType() {
        return box3D.getBoxType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ContainerImpl container = (ContainerImpl) o;

        return new EqualsBuilder()
                .append(box3D, container.box3D)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(box3D)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ContainerImpl{" +
                "box3D=" + box3D +
                '}';
    }
}
