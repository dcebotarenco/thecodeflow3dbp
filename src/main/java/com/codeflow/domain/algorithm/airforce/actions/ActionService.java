package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.Situation;

public class ActionService {

    private ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public void pack(Situation topologySituation) {
        actionRepository.findBy(topologySituation).run();
    }
}
