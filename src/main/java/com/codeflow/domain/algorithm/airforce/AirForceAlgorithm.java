package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.Result;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyFactory;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.container.ContainerRepository;
import com.codeflow.domain.orientation.Orientation;

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

    private Double totalContainerVolume;
    private Double totalItemVolume;

    private Double bestVolume;
    private Boolean packingBest;
    private Boolean hundredPercentPacked;
    private Long iterationsCount;
    private Boolean quit;
    private TopViewTopology topViewTopology;
    private LayerService layerService;
    private final TopologyService topologyService;
    private final ActionService actionService;
    private final ArticleRepository articleRepository;
    private final ContainerRepository containerRepository;
    private CornerFactory cornerFactory;
    private TopViewTopologyFactory topViewTopologyFactory;


    public AirForceAlgorithm(LayerService layerService,
                             TopologyService topologyService,
                             ActionService actionService,
                             ArticleRepository articleRepository,
                             ContainerRepository containerRepository,
                             CornerFactory cornerFactory,
                             TopViewTopologyFactory topViewTopologyFactory) {
        this.layerService = layerService;
        this.topologyService = topologyService;
        this.actionService = actionService;
        this.articleRepository = articleRepository;
        this.containerRepository = containerRepository;
        this.cornerFactory = cornerFactory;
        this.topViewTopologyFactory = topViewTopologyFactory;
    }

    @Override
    public Result run() {

        // Initialize
        totalContainerVolume = containerRepository.container().getWidth() * containerRepository.container().getHeight() * containerRepository.container().getHeight();
        totalItemVolume = articleRepository.receivedArticles().stream().map(article -> article.getWidth() * article.getHeight() * article.getLength())
                .reduce((v1, v2) -> v1 + v2).orElseThrow(() -> new IllegalStateException("Cannot calculate total volume of Items"));
        bestVolume = 0.0;
        packingBest = false;
        hundredPercentPacked = false;
        iterationsCount = 0L;
        quit = false;


        List<OrientationIteration> orientationIterations = new ArrayList<>();
        for (Orientation containerOrientation : containerRepository.container().getOrientations()) {
            List<Layer> layers = layerService.listCandidates(containerOrientation, articleRepository.receivedArticles());
            List<LayerIteration> layerIterations = new ArrayList<>();
            for (Layer layer : layers) {
                layerIterations.add(new LayerIteration(layer, topologyService, actionService));
            }
            TopViewTopology topViewTopology = topViewTopologyFactory.create(cornerFactory.create(containerOrientation.getWidth(), 0.));
            orientationIterations.add(new OrientationIteration(containerOrientation, layerIterations, topViewTopology));
        }

        for (OrientationIteration orientationIteration : orientationIterations) {
            orientationIteration.run();
        }


        return null;
    }
}
