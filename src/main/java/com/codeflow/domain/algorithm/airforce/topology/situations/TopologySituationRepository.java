package com.codeflow.domain.algorithm.airforce.topology.situations;

import com.codeflow.domain.algorithm.airforce.topology.TopologySituation;

import java.util.ArrayList;
import java.util.List;

public class TopologySituationRepository {

    private List<TopologySituation> knownTopologySituations;

    public TopologySituationRepository() {
        knownTopologySituations = new ArrayList<>();
        knownTopologySituations.add(new NoBoxesOnTheLeftAndOnTheRight());
        knownTopologySituations.add(new NoBoxesOnTheLeft());
        knownTopologySituations.add(new NoBoxesOnTheRight());
        knownTopologySituations.add(new ThereAreBoxesOnBothSidesAndEqual());
        knownTopologySituations.add(new ThereAreBoxesOnBothSidesAndNotEqual());
    }

    public List<TopologySituation> findAll() {
        return knownTopologySituations;
    }

}
