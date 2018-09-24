package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituation;
import com.codeflow.domain.algorithm.airforce.topology.situations.TopologySituationRepository;

import java.util.Optional;

class TopologyServiceImpl implements TopologyService {

    private TopViewTopology topViewTopology;
    private TopologySituationRepository topologySituationRepository;
    private CornerFactory<Corner> cornerCornerFactory;


    TopologyServiceImpl(TopologySituationRepository topologySituationRepository,
                        CornerFactory<Corner> cornerCornerFactory) {
        this.topologySituationRepository = topologySituationRepository;
        this.cornerCornerFactory = cornerCornerFactory;
    }

    @Override
    public void initializeTopology(Double width, Double length) {
        topViewTopology = new TopViewTopologyImpl(cornerCornerFactory.create(width, length));
    }

    @Override
    public Situation analyzeTopology(Corner corner) {
        Optional<TopologySituation> possibleTopologySituation = topologySituationRepository.findAll().stream().filter(t -> t.match(corner, topViewTopology)).findFirst();
        if (possibleTopologySituation.isPresent()) {
            return possibleTopologySituation.get().situation();
        } else {
            throw new IllegalStateException("No topology situation matched");
        }
    }

    @Override
    public Corner findCornerWithSmallestLength() {
        return topViewTopology.findWithSmallestLength();
    }

    @Override
    public void addCornerBefore(Corner toAdd) {
        topViewTopology.addFirst(toAdd);
    }

    @Override
    public void updateSmallestCorner(Corner newSmallestCorner) {
        Corner cornerWithSmallestLength = findCornerWithSmallestLength();
        cornerWithSmallestLength.updateLength(newSmallestCorner.getLength());
        cornerWithSmallestLength.updateWidth(newSmallestCorner.getWidth());
    }

}
