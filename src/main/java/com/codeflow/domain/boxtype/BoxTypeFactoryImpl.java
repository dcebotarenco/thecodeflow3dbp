package com.codeflow.domain.boxtype;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

class BoxTypeFactoryImpl implements BoxTypeFactory<BoxType> {

    public BoxType create(Dimensions dimensions, List<Orientation> orientations) {
        return new BoxTypeImpl(dimensions, orientations);
    }
}
