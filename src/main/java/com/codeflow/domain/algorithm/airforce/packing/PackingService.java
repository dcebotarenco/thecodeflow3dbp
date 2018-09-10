package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.Situation;

public class PackingService {

    private ActionRepository actionRepository;

    public PackingService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public void pack(Situation topologySituation) {
        actionRepository.findBy(topologySituation).run();
    }
}
