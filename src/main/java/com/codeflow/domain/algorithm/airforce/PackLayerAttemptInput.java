package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.containertype.orientation.ContainerOrientation;

public class PackLayerAttemptInput {
    public Iteration iteration;
    public double layerThickness;
    public double remainpz;
    public double remainpy;
    public double packedy;
    public double packedVolume;
    public ContainerOrientation containerOrientation;

    public PackLayerAttemptInput(Iteration iteration,
                                 double layerThickness,
                                 double remainpz,
                                 double remainpy,
                                 double packedy,
                                 double packedVolume,
                                 ContainerOrientation containerOrientation) {
        this.iteration = iteration;
        this.layerThickness = layerThickness;
        this.remainpz = remainpz;
        this.remainpy = remainpy;
        this.packedy = packedy;
        this.packedVolume = packedVolume;
        this.containerOrientation = containerOrientation;
    }
}
