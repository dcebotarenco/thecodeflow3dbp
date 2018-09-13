package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

import java.util.List;

public interface TopViewTopology {
    List<Corner> getRightCorners();

    boolean hasCornerOnLeft(Corner corner);

    boolean hasCornerOnRight(Corner corner);

    Corner getRightCorner(Corner corner);

    Corner getLeftCorner(Corner corner);
}
