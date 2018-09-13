package com.codeflow.domain.position;

public interface PositionFactory<P extends Position> {

    P create(Double x, Double y, Double z);
}
