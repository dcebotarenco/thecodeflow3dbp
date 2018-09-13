package com.codeflow.domain.orientation;

import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.factoryproducer.FactoryProducer;

public class OrientationFactoryProducer implements FactoryProducer<OrientationFactory<Orientation>> {

    private DimensionsFactory dimensionsFactory;

    public OrientationFactoryProducer(DimensionsFactory dimensionsFactory) {
        this.dimensionsFactory = dimensionsFactory;
    }

    @Override
    public OrientationFactory<Orientation> defaultFactory() {
        return new OrientationFactoryImpl(dimensionsFactory);
    }
}
