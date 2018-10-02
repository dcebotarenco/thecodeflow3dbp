package com.codeflow.domain.articletype;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.articletype.orientation.ArticleOrientationImpl;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Article that has to be packed
 */
public class ArticleTypeImpl implements ArticleType {
    private BoxType<ArticleOrientation> boxType;

    public ArticleTypeImpl(Double width, Double height, Double length) {

        this.boxType = new BoxTypeImpl<>(width, height, length);
        /*As it is*/
        boxType.add(new ArticleOrientationImpl(width, height, length, this));
        /*Rotate on X*/
        boxType.add(new ArticleOrientationImpl(width, length, height, this));
        /*Rotate on Y*/
        boxType.add(new ArticleOrientationImpl(length, height, width, this));
        /*Rotate on Z*/
        boxType.add(new ArticleOrientationImpl(height, width, length, this));
        /*Rotate on X&Y*/
        boxType.add(new ArticleOrientationImpl(height, length, width, this));
        /*Rotate on Z&Y*/
        boxType.add(new ArticleOrientationImpl(length, width, height, this));
    }

    @Override
    public Double getWidth() {
        return boxType.getWidth();
    }

    @Override
    public Double getLength() {
        return boxType.getLength();
    }

    @Override
    public Double getHeight() {
        return boxType.getHeight();
    }

    @Override
    public Double getVolume() {
        return boxType.getVolume();
    }

    @Override
    public List<ArticleOrientation> getOrientations() {
        return boxType.getOrientations();
    }

    @Override
    public void add(ArticleOrientation orientation) {
        this.boxType.add(orientation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ArticleTypeImpl that = (ArticleTypeImpl) o;

        return new EqualsBuilder()
                .append(boxType, that.boxType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(boxType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ArticleTypeImpl{" +
                "boxType=" + boxType +
                '}';
    }
}
