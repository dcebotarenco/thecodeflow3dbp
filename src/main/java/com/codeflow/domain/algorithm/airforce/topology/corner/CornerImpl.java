package com.codeflow.domain.algorithm.airforce.topology.corner;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class CornerImpl implements Corner {
    private Double width;
    private Double length;

    CornerImpl(Double width, Double length) {
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
        return "Corner{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
