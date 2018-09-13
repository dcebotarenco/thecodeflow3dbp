package com.codeflow.domain.container.orientation;

import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;

class ContainerOrientationFactoryImpl implements ContainerOrientationFactory {

    private OrientationFactory<Orientation> orientationFactory;

    public ContainerOrientationFactoryImpl(OrientationFactory<Orientation> orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public ContainerOrientation create(Double width, Double height, Double length) {
        Orientation orientation = orientationFactory.create(width, height, length);
        return new ContainerOrientationImpl(orientation);
    }
}
