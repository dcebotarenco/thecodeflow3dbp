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

    public Run(AlgorithmInputData algorithmInputData,
               LayerService layerService,
               IterationSessionRepository iterationSessionRepository) {
        this.algorithmInputData = algorithmInputData;
        this.layerService = layerService;
        this.iterationSessionRepository = iterationSessionRepository;
    }


    public void start() {
        boolean hundredPercentPackedPerSearch = false;
        ContainerType containerType = algorithmInputData.getContainerType();
        for (ContainerOrientation containerOrientation : containerType.getOrientations()) {
            List<Layer> layers = layerService.listCandidates(containerOrientation, algorithmInputData.getArticleTypes());
            for (Layer layer : layers) {
                Iteration iteration = new Iteration(
                        new IterationSession(
                                algorithmInputData.getArticleTypes(),
                                layer,
                                containerOrientation),
                        layerService);
                IterationSession output = iteration.start();
                iterationSessionRepository.save(output);
                if (output.hundredPercentPacked) {
                    hundredPercentPackedPerSearch = true;
                    break;
                }
            }
            if (hundredPercentPackedPerSearch) {
                break;
            }
        }
    }

}
