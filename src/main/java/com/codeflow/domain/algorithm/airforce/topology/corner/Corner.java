package com.codeflow.domain.algorithm.airforce.topology.corner;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;

public interface Corner {
    Double getWidth();

    Double getLength();

    void updateLength(Double length);

    void updateWidth(Double width);

    void setTopology(TopViewTopology topViewTopology);

    boolean hasCornerOnLeft();

    boolean hasCornerOnRight();

    Corner getLeft();

    Corner getRight();
}
