package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.Situation;
import com.codeflow.domain.algorithm.airforce.topology.Corner;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopologySituation;

import static com.codeflow.domain.algorithm.airforce.Situation.NO_BOXES_ON_THE_LEFT_SIDE;

public class NoBoxesOnTheLeft extends TopologySituation {


    @Override
    protected boolean match(Corner corner, TopViewTopology topViewTopology) {
        return !topViewTopology.hasCornerOnLeft(corner) && topViewTopology.hasCornerOnRight(corner);
    }

    @Override
    public Situation situation() {
        return NO_BOXES_ON_THE_LEFT_SIDE;
    }
}
