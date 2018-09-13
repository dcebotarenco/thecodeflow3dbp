package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;

public interface ActionRepository<E extends Action> {
    E findBy(Situation topologySituation);
}
