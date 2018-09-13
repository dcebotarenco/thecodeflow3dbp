package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public interface TopViewTopologyFactory<T extends TopViewTopology> {

    T create(Corner firstCorner);
}
