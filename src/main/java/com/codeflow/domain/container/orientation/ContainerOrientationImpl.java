package com.codeflow.domain.container.orientation;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class ContainerOrientationImpl implements ContainerOrientation {

    private Orientation orientation;
    private Double packedVolume;
    private Double packedHeight;
    private Double remainHeight;
    private Double remainLength;

    ContainerOrientationImpl(Orientation orientation) {
        this.orientation = orientation;
        this.packedVolume = 0D;
        this.packedHeight = 0D;
        this.remainHeight = orientation.getHeight();
        this.remainLength = orientation.getLength();
    }

    @Override
    public Dimensions getDimensions() {
        return orientation.getDimensions();
    }

    @Override
    public Double getWidth() {
        return orientation.getWidth();
    }

    @Override
    public Double getLength() {
        return orientation.getLength();
    }

    @Override
    public Double getHeight() {
        return orientation.getHeight();
    }

    @Override
    public Double getVolume() {
        return orientation.getVolume();
    }

    @Override
    public boolean fit(Orientation orientation) {
        return this.orientation.fit(orientation);
    }

    @Override
    public Double getPackedVolume() {
        return packedVolume;
    }

    @Override
    public Double getPackedHeight() {
        return packedHeight;
    }

    @Override
    public Double getRemainHeight() {
        return remainHeight;
    }

    @Override
    public Double getRemainLength() {
        return remainLength;
    }

    public void pack(ArticleOrientation articleOrientation) {
        packedVolume = packedVolume + articleOrientation.getVolume();
    }

    @Override
    public boolean allVolumePacked() {
        return (getVolume() - packedVolume) == 0D;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ContainerOrientationImpl that = (ContainerOrientationImpl) o;

        return new EqualsBuilder()
                .append(orientation, that.orientation)
                .append(packedVolume, that.packedVolume)
                .append(packedHeight, that.packedHeight)
                .append(remainHeight, that.remainHeight)
                .append(remainLength, that.remainLength)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(orientation)
                .append(packedVolume)
                .append(packedHeight)
                .append(remainHeight)
                .append(remainLength)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ContainerOrientationImpl{" +
                "orientation=" + orientation +
                ", packedVolume=" + packedVolume +
                ", packedHeight=" + packedHeight +
                ", remainHeight=" + remainHeight +
                ", remainLength=" + remainLength +
                '}';
    }
}
