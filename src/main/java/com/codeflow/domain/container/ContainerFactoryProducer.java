package com.codeflow.domain.container;

import com.codeflow.domain.box.Box3DFactory;
import com.codeflow.domain.factoryproducer.FactoryProducer;

public class ContainerFactoryProducer implements FactoryProducer<ContainerFactory> {
    private Box3DFactory box3DFactory;

    public ContainerFactoryProducer(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }

    @Override
    public ContainerFactory defaultFactory() {
        return new ContainerFactoryImpl(box3DFactory);
    }
}
