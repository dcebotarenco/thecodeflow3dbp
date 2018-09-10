package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.Situation;
import com.codeflow.domain.algorithm.airforce.topology.Corner;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopologySituation;

public class ThereAreBoxesOnBothSidesAndEqual extends TopologySituation {

    @Override
    protected boolean match(Corner corner, TopViewTopology topViewTopology) {
        return topViewTopology.hasCornerOnRight(corner) && topViewTopology.hasCornerOnLeft(corner)
                && topViewTopology.getRightCorner(corner).equals(topViewTopology.getLeftCorner(corner));
    }

    @Override
    public Situation situation() {
        return Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_EQUAL;
    }
}
