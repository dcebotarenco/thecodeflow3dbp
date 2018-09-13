package com.codeflow.domain.orientation;

public interface OrientationFactory<E extends Orientation> {
    E create(Double width, Double height, Double length);
}
