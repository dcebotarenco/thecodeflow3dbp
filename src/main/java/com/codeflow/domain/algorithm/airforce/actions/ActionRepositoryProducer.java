package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerFactory;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionFactory;
import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class ActionRepositoryProducer implements RepositoryProducer<ActionRepository> {

    private final SearchingService searchingService;
    private final GapFactory gapFactory;
    private final PackingService packingService;
    private final PositionFactory<Position> positionFactory;
    private final TopologyService topologyService;
    private final CornerFactory<Corner> cornerFactory;
    private final LayerFactory<Layer> layerFactory;

    public ActionRepositoryProducer(SearchingService searchingService,
                                    GapFactory gapFactory,
                                    PackingService packingService,
                                    PositionFactory<Position> positionFactory,
                                    TopologyService topologyService,
                                    CornerFactory<Corner> cornerFactory,
                                    LayerFactory<Layer> layerFactory) {
        this.searchingService = searchingService;
        this.gapFactory = gapFactory;
        this.packingService = packingService;
        this.positionFactory = positionFactory;
        this.topologyService = topologyService;
        this.cornerFactory = cornerFactory;
        this.layerFactory = layerFactory;
    }

    @Override
    public ActionRepository defaultRepository() {
        return new ActionRepositoryImpl(searchingService, gapFactory, packingService, positionFactory, topologyService, cornerFactory, layerFactory);
    }
}
