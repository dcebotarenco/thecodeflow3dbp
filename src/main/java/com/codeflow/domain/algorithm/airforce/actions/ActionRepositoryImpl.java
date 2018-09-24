package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerFactory;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionFactory;

import java.util.HashMap;
import java.util.Map;

class ActionRepositoryImpl implements ActionRepository<Action> {

    private Map<Situation, Action> situationRunnableMap;

    ActionRepositoryImpl(SearchingService searchingService,
                         GapFactory gapFactory,
                         PackingService packingService,
                         PositionFactory<Position> positionFactory,
                         TopologyService topologyService,
                         CornerFactory<Corner> cornerFactory,
                         LayerFactory<Layer> layerFactory) {
        situationRunnableMap = new HashMap<>();
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_RIGHT_AND_LEFT_SIDES, new NoBoxesOnTheRightAndLeftSidesAction(searchingService,
                gapFactory,
                packingService,
                positionFactory, topologyService, cornerFactory, layerFactory));
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_LEFT_SIDE, new NoBoxesOnTheLeftAction());
        situationRunnableMap.put(Situation.NO_BOXES_ON_THE_RIGHT_SIDE, new NoBoxesOnTheRightAction());
        situationRunnableMap.put(Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_EQUAL, new ThereAreBoxesOnBothSidesAndEqualAction());
        situationRunnableMap.put(Situation.THERE_ARE_BOXES_ON_BOTH_SIDES_AND_NOT_EQUAL, new ThereAreBoxesOnBothSidesAndNotEqualAction());
    }

    @Override
    public Action findBy(Situation topologySituation) {
        return situationRunnableMap.get(situationRunnableMap);
    }
}
