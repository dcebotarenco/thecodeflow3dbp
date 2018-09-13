package com.codeflow.domain.box;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface Box3D<E extends Orientation> {
    Long getId();

    Dimensions getDimensions();

    Double getWidth();

    Double getLength();

    Double getHeight();

    List<E> getOrientations();

    BoxType<E> getBoxType();
}
