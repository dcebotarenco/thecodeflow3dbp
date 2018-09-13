package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

abstract class BoxResult {
    private ArticleOrientation orientation;
    private Position position;

    BoxResult(ArticleOrientation orientation, Position position) {
        this.orientation = orientation;
        this.position = position;
    }


    public ArticleOrientation getOrientation() {
        return orientation;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BoxResult boxResult = (BoxResult) o;

        return new EqualsBuilder()
                .append(orientation, boxResult.orientation)
                .append(position, boxResult.position)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(orientation)
                .append(position)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BoxResult{" +
                "orientation=" + orientation +
                ", position=" + position +
                '}';
    }
}
