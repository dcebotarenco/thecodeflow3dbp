package com.codeflow.domain.orientation;

import com.codeflow.domain.boxes.Dimensions3D;
import com.codeflow.domain.boxes.Dimensions3DFactory;

public class OrientationFactory {

    private Dimensions3DFactory dimensions3DFactory;

    public OrientationFactory(Dimensions3DFactory dimensions3DFactory) {

        this.dimensions3DFactory = dimensions3DFactory;
    }

    public Orientation create(Double width, Double height, Double length) {
        Dimensions3D dimensions3D = dimensions3DFactory.create(width, height, length);
        return new Orientation(dimensions3D);
    }
}
