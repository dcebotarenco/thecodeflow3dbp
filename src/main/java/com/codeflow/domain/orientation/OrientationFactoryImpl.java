package com.codeflow.domain.orientation;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.dimension.DimensionsFactory;

class OrientationFactoryImpl implements OrientationFactory<Orientation> {

    private DimensionsFactory dimensions3DFactory;

    OrientationFactoryImpl(DimensionsFactory dimensions3DFactory) {

        this.dimensions3DFactory = dimensions3DFactory;
    }

    public Orientation create(Double width, Double height, Double length) {
        Dimensions dimensions = dimensions3DFactory.create(width, height, length);
        return new OrientationImpl(dimensions);
    }
}
