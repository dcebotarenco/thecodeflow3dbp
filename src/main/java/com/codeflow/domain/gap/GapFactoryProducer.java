package com.codeflow.domain.gap;

import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.factoryproducer.FactoryProducer;

public class GapFactoryProducer implements FactoryProducer<GapFactory> {

    private Box3DFactory box3DFactory;

    public GapFactoryProducer(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }

    @Override
    public GapFactory defaultFactory() {
        return new GapFactoryImpl(box3DFactory);
    }
}
