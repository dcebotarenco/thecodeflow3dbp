package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

public class SearchResult {
    private ArticleOrientation bestFitInRequired;
    private ArticleOrientation bestFitBiggerThenRequired;

    public void addBestFitInRequired(ArticleOrientation orientation) {
        bestFitInRequired = orientation;
    }

    public void addBestFitBiggerThenRequired(ArticleOrientation orientation) {
        bestFitBiggerThenRequired = orientation;
    }

    public Optional<ArticleOrientation> getBestFitInRequired() {
        return Optional.ofNullable(bestFitInRequired);
    }

    public Optional<ArticleOrientation> getBestFitBiggerThenRequired() {
        return Optional.ofNullable(bestFitBiggerThenRequired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        return new EqualsBuilder()
                .append(bestFitInRequired, that.bestFitInRequired)
                .append(bestFitBiggerThenRequired, that.bestFitBiggerThenRequired)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(bestFitInRequired)
                .append(bestFitBiggerThenRequired)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "bestFitInRequired=" + bestFitInRequired +
                ", bestFitBiggerThenRequired=" + bestFitBiggerThenRequired +
                '}';
    }
}
