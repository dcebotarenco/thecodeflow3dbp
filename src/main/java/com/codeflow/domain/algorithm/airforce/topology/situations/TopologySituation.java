package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public interface TopologySituation {
    boolean match(Corner corner, TopViewTopology topViewTopology);

    Situation situation();

}
