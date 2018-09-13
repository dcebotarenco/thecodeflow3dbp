package com.codeflow.domain.algorithm.airforce.topology.corner;

public interface CornerFactory<C extends Corner> {

    C create(Double width, Double length);
}
