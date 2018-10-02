package com.codeflow.domain.orientation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrientationImpl implements Orientation {

    private final Double width;
    private final Double height;
    private final Double length;
    private final Double volume;

    public OrientationImpl(Double width, Double height, Double length) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.volume = width * height * length;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public Double getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrientationImpl that = (OrientationImpl) o;

        return new EqualsBuilder()
                .append(width, that.width)
                .append(height, that.height)
                .append(length, that.length)
                .append(volume, that.volume)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(width)
                .append(height)
                .append(length)
                .append(volume)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "OrientationImpl{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", volume=" + volume +
                '}';
    }

    public boolean fit(Orientation orientation) {
        return this.getWidth() >= orientation.getWidth() &&
                this.getHeight() >= orientation.getHeight() &&
                this.getLength() >= orientation.getLength();
    }

}
