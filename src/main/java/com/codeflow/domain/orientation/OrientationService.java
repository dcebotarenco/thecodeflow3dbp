package com.codeflow.domain.orientation;

import com.codeflow.domain.dimension.Dimensions;

import java.util.List;

public interface OrientationService<E extends Orientation> {
    List<E> calculateOrientations(Dimensions dimensions);
}
