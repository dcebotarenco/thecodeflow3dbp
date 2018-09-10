package com.codeflow.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ContainerDTO {
    private Long id;
    private Double width;
    private Double length;
    private Double height;

    public ContainerDTO(Long id, Double width, Double length, Double height) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Long getId() {
        return id;
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
    public String toString() {
        return "ContainerDTO{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ContainerDTO that = (ContainerDTO) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(width, that.width)
                .append(length, that.length)
                .append(height, that.height)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(width)
                .append(length)
                .append(height)
                .toHashCode();
    }
}
