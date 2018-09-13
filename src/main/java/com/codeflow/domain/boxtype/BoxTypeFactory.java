package com.codeflow.domain.boxtype;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface BoxTypeFactory<E extends BoxType> {

    E create(Dimensions dimensions, List<Orientation> orientations);
}
