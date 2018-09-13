package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public interface Action {

    void act(Corner cornerWithSmallestLength,
             Double remainingHeight,
             Double remainingLength,
             Layer layer);
}
