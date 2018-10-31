package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.PackResult;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.layer.LayerServiceImpl;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.iteration.IterationResult;
import com.codeflow.domain.iteration.IterationSessionRepository;
import com.codeflow.domain.position.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Collections.reverseOrder;

/**
 * Our model packs as many boxes as possible in a given container while selecting the suitable boxes from a given box set.
 * This property makes our approach more realistic. The model is also able to act rectangular boxes in any orientation.
 * Actually, our model not only packs rectangular boxes in any orientation, it also packs according to different orientations of the pallet.
 * In other words, it builds walls or layers along any of the six faces of the given container if all three pallet dimensions are not the same.
 * The model basically builds a new packing during each iteration. Our approach does not limit the number of different boxes in each layer.
 * It may act any number of different boxes within a layer if their surfaces make a good match to reduce the unpacked gaps within the layer.
 * This property makes it robust. Our heuristic employs both layer packing and wall building approaches. There are some other important methods
 * that our program uses to act boxes efficiently and quickly. One of them is packing a sublayer into any of the available unused space in the
 * last packed layer, which we call a layer-in-layer packing approach. Another new, and the most important, feature of our heuristic is an adaptation
 * of human behavior and intelligence to decide which box to act. Considerable improvement also comes from the data structure we employ. The output of our
 * program contains x, y, z coordinates and x, y, z dimensions of the packed orientation of each packed box.
 */
public class AirForceAlgorithm implements Algorithm {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirForceAlgorithm.class);

    private LayerService layerService;
    private IterationSessionRepository iterationSessionRepository;

    public AirForceAlgorithm() {
        this.layerService = new LayerServiceImpl();
        this.iterationSessionRepository = new IterationSessionRepository();
    }


    @Override
    public PackResult run(AlgorithmInputData algorithmInputData) {
        Run run = new Run(algorithmInputData, layerService, iterationSessionRepository);
        run.start();
        IterationResult translateResult = findBestResult();
        report(translateResult);
        return new PackResult(translateResult);
    }

    private IterationResult findBestResult() {
        List<IterationSession> sessions = iterationSessionRepository.getSessions();
        ArrayList<IterationSession> iterationSessions = new ArrayList<>(sessions);
        iterationSessions.sort(reverseOrder(Comparator.comparingDouble(s -> s.packedVolume)));
        Optional<IterationSession> best = iterationSessions.stream().findFirst();
        if (best.isPresent()) {
            return new IterationResult(best.get()).translate();
        } else {
            throw new IllegalStateException("No best Result");
        }
    }

    private void report(IterationResult byResult) {
        System.out.println();
        System.out.println();
        for (Map.Entry<Position, ArticleOrientation> entry : byResult.getPacked().entrySet()) {
            Position p = entry.getKey();
            ArticleOrientation a = entry.getValue();
            ArticleType boxType = a.getBoxType();
            System.out.printf("%.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f\n", boxType.getWidth(), boxType.getHeight(), boxType.getLength(), p.getX(), p.getY(), p.getZ(), a.getWidth(), a.getHeight(), a.getLength());
        }
    }
}
