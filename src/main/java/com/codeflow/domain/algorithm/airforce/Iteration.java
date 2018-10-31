package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.IterationResult;
import com.codeflow.domain.iteration.IterationResultRepository;

public class Iteration {
    private final LayerService layerService;
    private final ContainerOrientation containerOrientation;
    private IterationSession session;
    private IterationResultRepository iterationResultRepository;
    private Run run;

    public double packedVolume;
    public double packedy;
    public boolean packing;
    public double layerThickness;
    public double remainpy;
    public double remainpz;
    public long packedItemCount;
    public double percentageContainerUsed;
    public long currentIndexOfContainerOrientation;
    public long currentIndexOfLayer;

    public Iteration(
            IterationSession session,
            LayerService layerService,
            IterationResultRepository iterationResultRepository,
            Layer layer,
            Run run,
            ContainerOrientation containerOrientation,
            long containerOrientationIndex,
            long currentIndexOfLayer) {
        this.session = session;
        this.layerService = layerService;
        this.iterationResultRepository = iterationResultRepository;
        this.run = run;
        this.packedVolume = 0;
        this.packedy = 0;
        this.packing = true;
        this.layerThickness = layer.getHeight();
        this.containerOrientation = containerOrientation;
        this.remainpy = containerOrientation.getHeight();
        this.remainpz = containerOrientation.getLength();
        this.packedItemCount = 0;
        this.currentIndexOfContainerOrientation = containerOrientationIndex;
        this.currentIndexOfLayer = currentIndexOfLayer;
    }

    public void run() {
        do {
            PackLayer packLayer = new PackLayer(session, layerService, containerOrientation, run, this);
            packLayer.run();

        } while (packing);

        if ((packedVolume > run.bestVolume)) {
            run.bestVolume = packedVolume;
            run.bestVariantPerRequest = currentIndexOfContainerOrientation;
            run.bestIterationPerRequest = currentIndexOfLayer;
            iterationResultRepository.save(run.bestVariantPerRequest, run.bestIterationPerRequest, new IterationResult(containerOrientation, session.getPackedTypes(), session.getRemainingToPack()));
        }
        this.percentageContainerUsed = run.bestVolume * 100 / containerOrientation.getVolume();
    }


}
