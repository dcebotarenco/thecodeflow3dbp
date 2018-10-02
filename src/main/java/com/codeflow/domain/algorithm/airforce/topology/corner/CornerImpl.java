package com.codeflow.domain.algorithm.airforce.topology.corner;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CornerImpl implements Corner {
    private Double width;
    private Double length;
    private TopViewTopology topViewTopology;

    public CornerImpl(Double width, Double length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public void updateLength(Double length) {
        this.length = length;
    }

    @Override
    public void updateWidth(Double width) {
        this.width = width;
    }

    @Override
    public void setTopology(TopViewTopology topViewTopology) {
        this.topViewTopology = topViewTopology;
    }

    @Override
    public boolean hasCornerOnLeft() {
        return topViewTopology.hasCornerOnLeft(this);
    }

    @Override
    public boolean hasCornerOnRight() {
        return topViewTopology.hasCornerOnRight(this);
    }

    @Override
    public Corner getLeft() {
        return topViewTopology.getLeftCorner(this);
    }

    @Override
    public Corner getRight() {
        return topViewTopology.getRightCorner(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CornerImpl corner = (CornerImpl) o;

        return new EqualsBuilder()
                .append(width, corner.width)
                .append(length, corner.length)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(width)
                .append(length)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "[" + width +
                "," + length + "]";
    }
}
