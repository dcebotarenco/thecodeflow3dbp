package com.codeflow.domain.boxtype;

import com.codeflow.domain.factoryproducer.FactoryProducer;

public class BoxTypeFactoryProducer implements FactoryProducer<BoxTypeFactory<BoxType>> {
    @Override
    public BoxTypeFactory<BoxType> defaultFactory() {
        return new BoxTypeFactoryImpl();
    }
}
