package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.Situation;

import java.util.HashMap;
import java.util.Map;

public class ActionRepository {

    Map<Situation, Runnable> situationRunnableMap;

    public ActionRepository() {
        situationRunnableMap = new HashMap<>();
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_RIGHT_AND_LEFT_SIDES, new NoBoxesOnTheRightAndLeftSidesAction());
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_LEFT_SIDE, new NoBoxesOnTheLeftAction());
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_RIGHT_SIDE, new NoBoxesOnTheRightAction());
        situationRunnableMap.put(Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_EQUAL, new ThereAreBoxesOnBothSidesAndEqualAction());
        situationRunnableMap.put(Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_NOT_EQUAL, new ThereAreBoxesOnBothSidesAndNotEqualAction());
    }

    public Runnable findBy(Situation topologySituation) {
        return situationRunnableMap.get(situationRunnableMap);
    }
}
