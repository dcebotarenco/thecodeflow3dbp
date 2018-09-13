package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.orientation.Orientation;

public interface PackingService {
    void pack(Orientation orientation, Position position);
}
