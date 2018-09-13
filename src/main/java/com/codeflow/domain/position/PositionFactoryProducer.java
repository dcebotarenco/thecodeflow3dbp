package com.codeflow.domain.position;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class PositionFactoryProducer implements FactoryProducer<PositionFactory<Position>> {
    @Override
    public PositionFactory<Position> defaultFactory() {
        return new PositionFactoryImpl();
    }
}
