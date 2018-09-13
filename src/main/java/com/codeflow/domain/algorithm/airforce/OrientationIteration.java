package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.container.orientation.ContainerOrientation;

import java.util.List;

public class OrientationIteration implements Runnable {


    private ContainerOrientation containerOrientation;
    private List<LayerIteration> layerIterations;
    private TopViewTopology topViewTopology;


    OrientationIteration(ContainerOrientation containerOrientation,
                         List<LayerIteration> layerIterations,
                         TopViewTopology topViewTopology) {
        this.layerIterations = layerIterations;
        this.containerOrientation = containerOrientation;
        this.topViewTopology = topViewTopology;
    }

    @Override
    public void run() {
        for (LayerIteration layerIteration : layerIterations) {
            layerIteration.run(this);
        }
    }

    public ContainerOrientation getContainerOrientation() {
        return containerOrientation;
    }
}
