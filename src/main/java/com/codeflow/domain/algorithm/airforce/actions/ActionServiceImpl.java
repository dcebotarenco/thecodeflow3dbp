package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;

class ActionServiceImpl implements ActionService {

    private ActionRepository actionRepository;

    ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public void act(Situation topologySituation, Corner cornerWithSmallestLength, Double remainingHeight, Double remainingLength, Layer layer) {
        actionRepository.findBy(topologySituation).act(cornerWithSmallestLength, remainingHeight, remainingLength, layer);
    }
}
