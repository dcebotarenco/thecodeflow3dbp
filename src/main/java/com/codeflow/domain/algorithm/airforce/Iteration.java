package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.iteration.IterationSessionRepository;

public class Iteration {
    private final LayerService layerService;
    private IterationSession session;
    private IterationSessionRepository iterationSessionRepository;
    private Run run;


    public Iteration(
            IterationSession session,
            LayerService layerService,
            IterationSessionRepository iterationSessionRepository,
            Run run) {
        this.session = session;
        this.layerService = layerService;
        this.iterationSessionRepository = iterationSessionRepository;
        this.run = run;
    }

    public void run() {
        do {
            PackLayer packLayer = new PackLayer(session, layerService, run);
            packLayer.run();

        } while (session.packing);

        this.session.percentageContainerUsed = run.bestVolume * 100 / session.containerOrientation.getVolume();
        iterationSessionRepository.save(session);
    }
}
