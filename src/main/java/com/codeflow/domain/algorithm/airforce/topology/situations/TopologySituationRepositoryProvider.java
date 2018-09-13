package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class TopologySituationRepositoryProvider implements RepositoryProducer<TopologySituationRepository> {
    @Override
    public TopologySituationRepository defaultRepository() {
        return new TopologySituationRepositoryImpl();
    }
}
