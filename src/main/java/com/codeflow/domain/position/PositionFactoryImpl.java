package com.codeflow.domain.position;

class PositionFactoryImpl implements PositionFactory<Position> {
    @Override
    public Position create(Double x, Double y, Double z) {
        return new PositionImpl(x, y, z);
    }
}
