package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.containertype.orientation.ContainerOrientation;

public class PackLayerAttemptInput {
    public IterationSession iterationSession;
    public double layerThickness;
    public double remainpz;
    public double remainpy;
    public double packedy;
    public ContainerOrientation containerOrientation;

    public PackLayerAttemptInput(IterationSession session,
                                 double layerThickness,
                                 double remainpz,
                                 double remainpy,
                                 double packedy,
                                 ContainerOrientation containerOrientation) {
        this.iterationSession = session;
        this.layerThickness = layerThickness;
        this.remainpz = remainpz;
        this.remainpy = remainpy;
        this.packedy = packedy;
        this.containerOrientation = containerOrientation;
    }
}
