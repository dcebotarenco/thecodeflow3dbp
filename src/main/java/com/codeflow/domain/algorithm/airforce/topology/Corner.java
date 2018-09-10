package com.codeflow.domain.algorithm.airforce.topology;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Corner {
    Double width;
    Double length;

    public Corner(Double width, Double length) {
        this.width = width;
        this.length =length;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Corner corner = (Corner) o;

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
