package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public class NoBoxesOnTheLeftAndOnTheRight implements TopologySituation {


    @Override
    public boolean match(Corner corner, TopViewTopology topViewTopology) {
        return !topViewTopology.hasCornerOnLeft(corner) && !topViewTopology.hasCornerOnRight(corner);
    }

    @Override
    public Situation situation() {
        return Situation.NO_BOXES_ON_THE_RIGHT_AND_LEFT_SIDES;
    }
}
