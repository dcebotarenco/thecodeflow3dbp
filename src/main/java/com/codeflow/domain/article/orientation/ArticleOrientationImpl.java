package com.codeflow.domain.article.orientation;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

class ArticleOrientationImpl implements ArticleOrientation {

    private Orientation orientation;

    ArticleOrientationImpl(Orientation orientation) {
        this.orientation = orientation;
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ArticleOrientationImpl that = (ArticleOrientationImpl) o;

        return new EqualsBuilder()
                .append(orientation, that.orientation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(orientation)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ArticleOrientationImpl{" +
                "orientation=" + orientation +
                '}';
    }
}
