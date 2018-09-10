package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.Situation;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepository;

import java.util.Optional;

public class TopologyService {

    private TopViewTopology topViewTopology;
    private TopologySituationRepository topologySituationRepository;


    public TopologyService(TopologySituationRepository topologySituationRepository) {
        this.topologySituationRepository = topologySituationRepository;
    }

    public void initializeTopology(Double width, Double length) {
        topViewTopology = new TopViewTopology(new Corner(width, length));
    }

    public Situation analyzeTopology(Corner corner) {
        Optional<TopologySituation> possibleTopologySituation = topologySituationRepository.findAll().stream().filter(t -> t.match(corner, topViewTopology)).findFirst();
        if (possibleTopologySituation.isPresent()) {
            return possibleTopologySituation.get().situation();
        } else {
            throw new IllegalStateException("No topology situation matched");
        }
    }

    public Corner findCornerWithSmallestLength() {
        return topViewTopology.findWithSmallestLength();
    }

}
