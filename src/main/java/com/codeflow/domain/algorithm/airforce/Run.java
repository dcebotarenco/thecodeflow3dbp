package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.iteration.IterationResultRepository;

import java.util.List;

public class Run {

    private final LayerService layerService;
    private final IterationResultRepository iterationResultRepository;
    private AlgorithmInputData algorithmInputData;
    public Double bestVolume;
    public Double totalItemVolume;
    public long bestIterationPerRequest;
    public long bestVariantPerRequest;
    public boolean hundredPercentPackedPerSearch;


    public Run(AlgorithmInputData algorithmInputData,
               LayerService layerService,
               IterationResultRepository iterationResultRepository) {
        this.algorithmInputData = algorithmInputData;
        this.layerService = layerService;
        this.iterationResultRepository = iterationResultRepository;
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
                IterationSession iterationSession = new IterationSession(algorithmInputData.getArticleTypes());
                Iteration iterationPerLayer = new Iteration(
                        iterationSession,
                        layerService,
                        iterationResultRepository,
                        layer, this, containerOrientation, containerIndex, currentIndexOfLayer);
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
