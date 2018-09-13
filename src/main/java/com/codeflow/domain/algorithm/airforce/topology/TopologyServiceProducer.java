package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepository;
import com.codeflow.domain.serviceproducer.ServiceProducer;

public class TopologyServiceProducer implements ServiceProducer<TopologyService> {
    private TopologySituationRepository topologySituationRepository;
    private CornerFactory<Corner> cornerFactory;

    public TopologyServiceProducer(TopologySituationRepository topologySituationRepository, CornerFactory<Corner> cornerFactory) {
        this.topologySituationRepository = topologySituationRepository;
        this.cornerFactory = cornerFactory;
    }

    @Override
    public TopologyService defaultService() {
        return new TopologyServiceImpl(topologySituationRepository, cornerFactory);
    }
}
