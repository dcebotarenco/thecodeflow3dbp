package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.Situation;

public abstract class TopologySituation {

    private Situation situation;

    protected abstract boolean match(Corner corner, TopViewTopology topViewTopology);

    public abstract Situation situation();

}
