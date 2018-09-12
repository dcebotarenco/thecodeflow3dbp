package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.topology.Corner;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.boxes.Orientation;

import java.util.List;

public class OrientationIteration implements Runnable {


    private Orientation orientation;
    private List<LayerIteration> layerIterations;
    private Double packedVolumePerIteration;
    private Double packedHeightPerIteration;
    private Double remaingHeight;
    private Double remaingLenght;
    private Long packedItemCount;
    private TopViewTopology topViewTopology;


    OrientationIteration(Orientation containerOrientation, List<LayerIteration> layerIterations) {
        this.layerIterations = layerIterations;
        this.orientation = containerOrientation;
        packedVolumePerIteration = 0.;
        packedHeightPerIteration = 0.;
        this.packedItemCount = 0L;
        remaingHeight = containerOrientation.getHeight();
        remaingLenght = containerOrientation.getLength();
        topViewTopology = new TopViewTopology(new Corner(containerOrientation.getWidth(), 0.));
    }

    @Override
    public void run() {
        for (LayerIteration layerIteration : layerIterations) {
            layerIteration.run(this);
        }
    }

    public Double getPackedHeightPerIteration() {
        return packedHeightPerIteration;
    }

    public void setPackedHeightPerIteration(Double packedHeightPerIteration) {
        this.packedHeightPerIteration = packedHeightPerIteration;
    }

    public TopViewTopology getTopViewTopology() {
        return topViewTopology;
    }
}
