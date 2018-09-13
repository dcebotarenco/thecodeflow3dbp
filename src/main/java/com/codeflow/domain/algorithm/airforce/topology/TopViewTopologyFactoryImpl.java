package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

class TopViewTopologyFactoryImpl implements TopViewTopologyFactory<TopViewTopology> {
    @Override
    public TopViewTopology create(Corner firstCorner) {
        return new TopViewTopologyImpl(firstCorner);
    }
}
