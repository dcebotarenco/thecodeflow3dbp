package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.IterationSessionRepository;

import java.util.List;

public class Run {

    private final LayerService layerService;
    private final IterationSessionRepository iterationSessionRepository;
    private AlgorithmInputData algorithmInputData;
    public Double bestVolume;
    public Double totalItemVolume;
    public boolean hundredPercentPackedPerSearch;


    public Run(AlgorithmInputData algorithmInputData,
               LayerService layerService,
               IterationSessionRepository iterationSessionRepository) {
        this.algorithmInputData = algorithmInputData;
        this.layerService = layerService;
        this.iterationSessionRepository = iterationSessionRepository;
        this.bestVolume = 0.0;
    }

    public Double totalVolume() {
        return algorithmInputData.getArticleTypes().entrySet().stream().map(t -> t.getKey().getVolume() * t.getValue()).reduce((v1, v2) -> v1 + v2).orElse(0D);
    }

    public void start() {
        this.totalItemVolume = totalVolume();
        ContainerType containerType = algorithmInputData.getContainerType();
        for (ContainerOrientation containerOrientation : containerType.getOrientations()) {
            long containerIndex = containerType.getOrientations().indexOf(containerOrientation);
            List<Layer> layers = layerService.listCandidates(containerOrientation, algorithmInputData.getArticleTypes());
            for (Layer layer : layers) {
                long currentIndexOfLayer = layers.indexOf(layer);
                Iteration iterationPerLayer = new Iteration(
                        new IterationSession(
                                algorithmInputData.getArticleTypes(),
                                layer,
                                containerOrientation,
                                containerIndex,
                                currentIndexOfLayer
                        ),
                        layerService,
                        iterationSessionRepository,
                        this);
                iterationPerLayer.run();
                if (this.hundredPercentPackedPerSearch) {
                    break;
                }
            }
            if (this.hundredPercentPackedPerSearch) {
                break;
            }
        }
    }

}
