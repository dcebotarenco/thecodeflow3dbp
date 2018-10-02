package com.codeflow.domain.boxtype;

import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public interface BoxType<E extends Orientation> {

    Double getWidth();

    Double getLength();

    Double getHeight();

    Double getVolume();

    List<E> getOrientations();

    void add(E orientation);
}
