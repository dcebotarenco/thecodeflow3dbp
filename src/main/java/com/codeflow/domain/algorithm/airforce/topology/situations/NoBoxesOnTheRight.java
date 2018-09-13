package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public class NoBoxesOnTheRight implements TopologySituation {


    @Override
    public boolean match(Corner corner, TopViewTopology topViewTopology) {
        return !topViewTopology.hasCornerOnRight(corner) && topViewTopology.hasCornerOnLeft(corner);
    }

    @Override
    public Situation situation() {
        return Situation.NO_BOXES_ON_THE_RIGHT_SIDE;
    }
}
