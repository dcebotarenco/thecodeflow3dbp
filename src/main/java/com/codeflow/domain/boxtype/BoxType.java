package com.codeflow.domain.boxtype;

import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface BoxType<E extends Orientation> {
    Dimensions getDimensions();

    List<E> getOrientations();
}
