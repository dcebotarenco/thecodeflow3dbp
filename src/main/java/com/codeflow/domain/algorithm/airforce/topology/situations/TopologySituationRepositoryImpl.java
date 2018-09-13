package com.codeflow.domain.algorithm.airforce.topology.situations;

import java.util.ArrayList;
import java.util.List;

class TopologySituationRepositoryImpl implements TopologySituationRepository {

    private List<TopologySituation> knownTopologySituations;

    TopologySituationRepositoryImpl() {
        knownTopologySituations = new ArrayList<>();
        knownTopologySituations.add(new NoBoxesOnTheLeftAndOnTheRight());
        knownTopologySituations.add(new NoBoxesOnTheLeft());
        knownTopologySituations.add(new NoBoxesOnTheRight());
        knownTopologySituations.add(new ThereAreBoxesOnBothSidesAndEqual());
        knownTopologySituations.add(new ThereAreBoxesOnBothSidesAndNotEqual());
    }

    @Override
    public List<TopologySituation> findAll() {
        return knownTopologySituations;
    }

}
