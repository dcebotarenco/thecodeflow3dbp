package com.codeflow.domain.algorithm.airforce.topology.corner;

class CornerFactoryImpl implements CornerFactory<Corner> {
    @Override
    public Corner create(Double width, Double length) {
        return new CornerImpl(width, length);
    }
}
