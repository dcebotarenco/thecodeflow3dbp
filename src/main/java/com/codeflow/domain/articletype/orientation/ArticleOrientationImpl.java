package com.codeflow.domain.articletype.orientation;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ArticleOrientationImpl implements ArticleOrientation {

    private Orientation orientation;
    private ArticleType boxType;

    public ArticleOrientationImpl(Double width, Double height, Double length, ArticleType boxType) {
        this.orientation = new OrientationImpl(width, height, length);
        this.boxType = boxType;
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

    @Override
    public ArticleType getBoxType() {
        return boxType;
    }
}
