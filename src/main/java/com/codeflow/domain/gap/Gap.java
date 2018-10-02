package com.codeflow.domain.gap;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.orientation.Orientation;

public interface Gap extends BoxType {
    boolean fit(Orientation orientation);

    boolean smallerThenHeight(Orientation orientation);
}
