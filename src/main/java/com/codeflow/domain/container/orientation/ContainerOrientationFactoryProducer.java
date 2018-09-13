package com.codeflow.domain.container.orientation;

import com.codeflow.domain.factoryproducer.FactoryProducer;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;

public class ContainerOrientationFactoryProducer implements FactoryProducer<ContainerOrientationFactory> {

    private OrientationFactory<Orientation> orientationFactory;

    public ContainerOrientationFactoryProducer(OrientationFactory<Orientation> orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public ContainerOrientationFactory defaultFactory() {
        return new ContainerOrientationFactoryImpl(orientationFactory);
    }
}
