package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public class ThereAreBoxesOnBothSidesAndNotEqual implements TopologySituation {

    @Override
    public boolean match(Corner corner, TopViewTopology topViewTopology) {
        return topViewTopology.hasCornerOnRight(corner) && topViewTopology.hasCornerOnLeft(corner)
                && !topViewTopology.getRightCorner(corner).equals(topViewTopology.getLeftCorner(corner));
    }

    @Override
    public Situation situation() {
        return Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_NOT_EQUAL;
    }
}
