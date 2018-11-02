package com.codeflow.application.client.container;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Container {
    private Double width;
    private Double length;
    private Double height;

    public Container(Double width, Double height, Double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }


    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Container that = (Container) o;

        return new EqualsBuilder()
                .append(width, that.width)
                .append(length, that.length)
                .append(height, that.height)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(width)
                .append(length)
                .append(height)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Container{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }
}
