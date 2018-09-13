package com.codeflow.domain.orientation;

import com.codeflow.domain.serviceproducer.ServiceProducer;

public class OrientationServiceProducer implements ServiceProducer<OrientationService> {

    private OrientationFactory orientationFactory;

    public OrientationServiceProducer(OrientationFactory orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public OrientationService defaultService() {
        return new OrientationServiceImpl(orientationFactory);
    }
}
