package com.codeflow.application.client;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ArticleType {
    private Double width;
    private Double length;
    private Double height;

    public ArticleType(Double width, Double height, Double length) {
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
    public String toString() {
        return "ArticleType{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ArticleType that = (ArticleType) o;

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
}
