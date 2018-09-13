package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;

public interface ActionService {
    void act(Situation topologySituation, Corner cornerWithSmallestLength, Double remainingHeight, Double remainingLength, Layer layer);
}
