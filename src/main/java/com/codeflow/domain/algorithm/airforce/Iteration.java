package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.LayerService;

public class Iteration {
    private final LayerService layerService;
    private IterationSession session;

    public Iteration(IterationSession session,
                     LayerService layerService) {
        this.session = session;
        this.layerService = layerService;
    }

    public IterationSession start() {
        do {
            pack();
            PackLayer packLayer = new PackLayer(session, session.getContainerOrientation(), layerService);
            packLayer.run();

        } while (notStopped());

        this.session.percentageContainerUsed = this.session.packedVolume * 100 / session.getContainerOrientation().getVolume();
        return session;
    }

    public void pack() {

    }

    boolean notStopped() {
        return session.packing;
    }
}
