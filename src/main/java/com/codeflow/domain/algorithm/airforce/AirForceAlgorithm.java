package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.Result;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.article.ArticleService;
import com.codeflow.domain.container.ContainerRepository;
import com.codeflow.domain.container.orientation.ContainerOrientation;

import java.util.ArrayList;
import java.util.List;

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

    private Double bestVolume;
    private Boolean packingBest;

    private LayerService layerService;
    private final TopologyService topologyService;
    private final ActionService actionService;
    private final ArticleRepository articleRepository;
    private final ContainerRepository containerRepository;
    private ArticleService articleService;


    public AirForceAlgorithm(LayerService layerService,
                             TopologyService topologyService,
                             ActionService actionService,
                             ArticleRepository articleRepository,
                             ContainerRepository containerRepository,
                             ArticleService articleService) {
        this.layerService = layerService;
        this.topologyService = topologyService;
        this.actionService = actionService;
        this.articleRepository = articleRepository;
        this.containerRepository = containerRepository;
        this.articleService = articleService;
    }

    @Override
    public Result run() {

        // Initialize
        bestVolume = 0.0;
        packingBest = false;

        List<OrientationIteration> orientationIterations = new ArrayList<>();
        for (ContainerOrientation containerOrientation : containerRepository.container().getOrientations()) {
            topologyService.initializeTopology(containerOrientation.getWidth(), 0.);
            List<Layer> layers = layerService.listCandidates(containerOrientation, articleRepository.receivedArticles());
            List<LayerIteration> layerIterations = new ArrayList<>();
            for (Layer layer : layers) {
                layerIterations.add(new LayerIteration(layer, topologyService, actionService, articleService));
            }
            orientationIterations.add(new OrientationIteration(containerOrientation, layerIterations, articleService));
        }

        for (OrientationIteration orientationIteration : orientationIterations) {
            orientationIteration.run();
        }


        return null;
    }
}
