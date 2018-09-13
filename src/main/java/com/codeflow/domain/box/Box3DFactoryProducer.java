package com.codeflow.domain.box;

import com.codeflow.domain.boxtype.BoxTypeFactory;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.factoryproducer.FactoryProducer;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationService;

public class Box3DFactoryProducer implements FactoryProducer<Box3DFactory> {
    private BoxTypeFactory boxTypeFactory;
    private DimensionsFactory dimensionsFactory;
    private OrientationService<Orientation> orientationService;

    public Box3DFactoryProducer(BoxTypeFactory boxTypeFactory, DimensionsFactory dimensionsFactory, OrientationService orientationService) {
        this.boxTypeFactory = boxTypeFactory;
        this.dimensionsFactory = dimensionsFactory;
        this.orientationService = orientationService;
    }

    @Override
    public Box3DFactory defaultFactory() {
        return new Box3DFactoryImpl(boxTypeFactory, dimensionsFactory, orientationService);
    }
}
