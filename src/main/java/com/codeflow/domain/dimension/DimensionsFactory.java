package com.codeflow.domain.dimension;

public interface DimensionsFactory<E extends Dimensions> {
    E create(Double width, Double height, Double length);
}
