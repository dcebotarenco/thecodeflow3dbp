package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;

public interface TopologyService {
    void initializeTopology(Double width, Double length);

    Situation analyzeTopology(Corner corner);

    Corner findCornerWithSmallestLength();

    void addCornerBefore(Corner toAdd);

    void updateSmallestCorner(Corner newSmallestCorner);
}
