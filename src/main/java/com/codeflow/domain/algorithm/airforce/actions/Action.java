package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.container.orientation.ContainerOrientation;

public interface Action {

    void act(Corner cornerWithSmallestLength,
             ContainerOrientation containerOrientation,
             Layer layer);
}
