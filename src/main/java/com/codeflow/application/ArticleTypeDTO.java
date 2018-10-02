package com.codeflow.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ArticleTypeDTO {
    private Double width;
    private Double length;
    private Double height;
    private Long number;

    public ArticleTypeDTO(Double width, Double length, Double height, Long number) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.number = number;
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

    public Long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "ArticleTypeDTO{" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ArticleTypeDTO that = (ArticleTypeDTO) o;

        return new EqualsBuilder()
                .append(width, that.width)
                .append(length, that.length)
                .append(height, that.height)
                .append(number, that.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(width)
                .append(length)
                .append(height)
                .append(number)
                .toHashCode();
    }
}
