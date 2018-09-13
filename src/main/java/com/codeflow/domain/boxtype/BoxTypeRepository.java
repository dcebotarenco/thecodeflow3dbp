package com.codeflow.domain.boxtype;

import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface BoxTypeRepository<E extends BoxType> {

    List<E> boxTypes();

    E findByOrientation(Orientation orientation);
}
