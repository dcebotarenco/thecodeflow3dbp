package com.codeflow.domain.boxtype;

import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class BoxTypeImpl<E extends Orientation> implements BoxType<E> {

    private final Double width;
    private final Double height;
    private final Double length;
    private final LinkedHashSet<E> orientations;
    private final Double volume;

    public BoxTypeImpl(Double width, Double height, Double length) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.volume = width * height * length;
        this.orientations = new LinkedHashSet<>();
    }

    public void add(E orientation) {
        this.orientations.add(orientation);
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
    public List<E> getOrientations() {
        return new ArrayList<>(orientations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoxTypeImpl<?> boxType = (BoxTypeImpl<?>) o;

        return new EqualsBuilder()
                .append(volume, boxType.volume)
                .append(width, boxType.width)
                .append(height, boxType.height)
                .append(length, boxType.length)
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
        return "BoxTypeImpl{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", volume=" + volume +
                '}';
    }
}
