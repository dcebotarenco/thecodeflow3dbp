package com.codeflow.domain.gap;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeImpl;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class GapImpl implements Gap {

    private BoxType<Orientation> boxType;

    public GapImpl(Double width, Double height, Double length) {
        boxType = new BoxTypeImpl<>(width, height, length);
    }

    @Override
    public Double getWidth() {
        return boxType.getWidth();
    }

    @Override
    public Double getLength() {
        return boxType.getLength();
    }

    @Override
    public Double getHeight() {
        return boxType.getHeight();
    }

    @Override
    public Double getVolume() {
        return boxType.getVolume();
    }

    @Override
    public List<Orientation> getOrientations() {
        throw new UnsupportedOperationException("No supported");
    }

    @Override
    public void add(Orientation orientation) {
        throw new UnsupportedOperationException("No supported");
    }

    public boolean fit(Orientation orientation) {
        return orientation.getWidth() <= this.getWidth() &&
                orientation.getHeight() <= this.getHeight() && orientation.getLength() <= this.getLength();
    }

    public boolean smallerThenHeight(Orientation orientation) {
        return orientation.getHeight() <= getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GapImpl gap = (GapImpl) o;

        return new EqualsBuilder()
                .append(boxType, gap.boxType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(boxType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "GapImpl{" +
                "boxType=" + boxType +
                '}';
    }
}
