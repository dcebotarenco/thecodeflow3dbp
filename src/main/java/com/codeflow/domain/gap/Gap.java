package com.codeflow.domain.gap;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.orientation.Orientation;

public interface Gap extends Box3D {
    boolean fit(Orientation orientation);

    boolean smallerThenHeight(Orientation orientation);
}
