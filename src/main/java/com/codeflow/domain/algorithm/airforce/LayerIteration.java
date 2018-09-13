package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.situations.Situation;
import com.codeflow.domain.container.Container;
import com.codeflow.domain.dimension.Dimension;
import com.codeflow.domain.orientation.Orientation;

/**
 * Algorithm iteration that act 1 {@link Orientation} and 1 {@link Layer}.
 * <p>
 * {@link LayerIteration} tie closely to the six possible {@link Orientation} of a {@link Container}.
 * </p>
 * During iterations, all six {@link Orientation} of the {@link Container} are packed.
 * Each unique {@link Orientation} of the {@link Container} is treated as a {@link Container} to act.
 * Obviously, if a {@link Container} has three identical {@link Dimension},
 * it has only one {@link Orientation}. In general, we have 1,2 or 6 {@link Orientation}s for 1,2 or 3 unique
 * {@link Dimension}s, respectively. In each {@link LayerIteration}, each {@link Orientation}
 * of the {@link Container} is packed once for each {@link Layer}.
 * <p>
 * Each {@link LayerIteration} begins packing with an initial {@link Layer} thickness taken from {@link Layer#getDimension()} value.
 * Thus, if we have 7 different {@link Dimension} values in our {@link java.util.List}s of {@link Layer} and
 * the {@link Container} has 3 unique {@link Dimension}s, the
 * program potentially performs 6*7=42 {@link LayerIteration}s
 * </p>
 */
public class LayerIteration {

    private Layer layer;
    private Double layerThickness;
    private TopologyService topologyService;
    private ActionService actionService;
    private Double layerinlayer;
    private Boolean layerDone;
    private Boolean packing;


    LayerIteration(Layer layer,
                   TopologyService topologyService,
                   ActionService actionService) {
        this.layer = layer;
        this.layerThickness = layer.getDimension();
        this.topologyService = topologyService;
        this.actionService = actionService;
    }

    void run(OrientationIteration orientationIteration) {
        packing = true;
        while (packing) {
            layerinlayer = 0.;
            layerDone = false;

            double lenx;
            double lenz;
            double lpz;

            if (layerThickness == 0) {
                packing = false;
                return;
            }

            do {
                Corner cornerWithSmallestLength = topologyService.findCornerWithSmallestLength();
                Situation topologySituation = topologyService.analyzeTopology(cornerWithSmallestLength);
                actionService.act(
                        topologySituation,
                        cornerWithSmallestLength,
                        orientationIteration.getRemainHeight(),
                        orientationIteration.getRemainLength(),
                        layer);
//                if (packedVolume == totalContainerVolume || packedVolume == totalItemVolume) {
//                    packing = false;
//                    hundredPercentPacked = true;
//
//                }
//                VolumeCheck();

            } while (layer.isDone());


        }

//            orientationIteration.setPackedHeightPerIteration(orientationIteration.getPackedHeightPerIteration() + layerThickness);
        //remainpy = py - packedy;

//            if (layerinlayer != 0 && !quit) {
////                        System.out.println("There is Layer in Layer");
//                prepackedy = packedy;
//                preremainpy = remainpy;
//                remainpy = layerThickness - prelayer;
//                packedy = packedy - layerThickness + prelayer;
//                remainpz = lilz;
//                //System.out.println("Assign 3 =" + layerinlayer);
//                layerThickness = layerinlayer;
//                layerDone = false;
//
//                PackLayer();
//
//                packedy = prepackedy;
//                remainpy = preremainpy;
//                remainpz = pz;
//            }

//            FindLayer(remainpy);

    }

    private void PackLayer(OrientationIteration orientationIteration) {


    }

    public Double getLayerThickness() {
        return layerThickness;
    }

}

