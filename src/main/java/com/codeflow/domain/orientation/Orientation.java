package com.codeflow.domain.orientation;

public interface Orientation {

    Double getWidth();

    Double getLength();

    Double getHeight();

    Double getVolume();

    boolean fit(Orientation orientation);

}
