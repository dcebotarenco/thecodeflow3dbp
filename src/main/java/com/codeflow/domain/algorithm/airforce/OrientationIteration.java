package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.orientation.Orientation;

import java.util.List;

public class OrientationIteration implements Runnable {


    private Orientation orientation;
    private List<LayerIteration> layerIterations;
    private Double packedVolumePerIteration;
    private Double packedHeightPerIteration;
    private Double remainHeight;
    private Double remainLength;
    private Long packedItemCount;
    private TopViewTopology topViewTopology;


    OrientationIteration(Orientation containerOrientation, List<LayerIteration> layerIterations, TopViewTopology topViewTopology) {
        this.layerIterations = layerIterations;
        this.orientation = containerOrientation;
        packedVolumePerIteration = 0.;
        packedHeightPerIteration = 0.;
        this.packedItemCount = 0L;
        remainHeight = containerOrientation.getHeight();
        remainLength = containerOrientation.getLength();
        this.topViewTopology = topViewTopology;
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

    Double getRemainHeight() {
        return remainHeight;
    }

    Double getRemainLength() {
        return remainLength;
    }
}
