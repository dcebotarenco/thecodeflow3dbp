package com.codeflow.domain.gap;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class GapImpl implements Gap {

    private Box3D box3D;

    GapImpl(Box3D box3DImpl) {
        this.box3D = box3DImpl;
    }

    public Long getId() {
        return box3D.getId();
    }

    public Dimensions getDimensions() {
        return box3D.getDimensions();
    }

    public Double getWidth() {
        return box3D.getWidth();
    }

    public Double getLength() {
        return box3D.getLength();
    }

    public Double getHeight() {
        return box3D.getHeight();
    }

    public List<Orientation> getOrientations() {
        return box3D.getOrientations();
    }

    public BoxType getBoxType() {
        return box3D.getBoxType();
    }

    public boolean fit(Orientation orientation) {
        return orientation.getWidth() <= this.getWidth() &&
                orientation.getHeight() <= this.getHeight() && orientation.getLength() <= this.getLength();
    }

    public boolean smallerThenHeight(Orientation orientation) {
        return orientation.getHeight() <= this.getDimensions().getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GapImpl gap = (GapImpl) o;

        return new EqualsBuilder()
                .append(box3D, gap.box3D)
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
        return "GapImpl{" +
                "box3D=" + box3D +
                '}';
    }
}
