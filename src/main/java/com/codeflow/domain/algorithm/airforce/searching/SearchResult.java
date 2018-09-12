package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.boxes.Orientation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

public class SearchResult {
    private Optional<BestFitInRequired> bestFitInRequired;
    private Optional<BestFitBiggerThenRequired> bestFitBiggerThenRequired;

    public SearchResult() {
        bestFitInRequired = Optional.empty();
        bestFitBiggerThenRequired = Optional.empty();

    }

    public void addBestFitInRequired(Orientation orientation, Position position) {
        bestFitInRequired = Optional.of(new BestFitInRequired(orientation, position));
    }

    public void addBestFitBiggerThenRequired(Orientation orientation, Position position) {
        bestFitBiggerThenRequired = Optional.of(new BestFitBiggerThenRequired(orientation, position));
    }

    public Optional<BestFitInRequired> getBestFitInRequired() {
        return bestFitInRequired;
    }

    public Optional<BestFitBiggerThenRequired> getBestFitBiggerThenRequired() {
        return bestFitBiggerThenRequired;
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