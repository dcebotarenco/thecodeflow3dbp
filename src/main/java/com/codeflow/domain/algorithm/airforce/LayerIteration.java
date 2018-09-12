package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.actions.ActionService;
import com.codeflow.domain.algorithm.airforce.topology.Corner;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.boxes.Orientation;

/**
 * Algorithm iteration that pack 1 {@link Orientation} and 1 {@link Layer}.
 * <p>
 * {@link LayerIteration} tie closely to the six possible {@link Orientation} of a {@link com.codeflow.domain.boxes.Container}.
 * </p>
 * During iterations, all six {@link Orientation} of the {@link com.codeflow.domain.boxes.Container} are packed.
 * Each unique {@link Orientation} of the {@link com.codeflow.domain.boxes.Container} is treated as a {@link com.codeflow.domain.boxes.Container} to pack.
 * Obviously, if a {@link com.codeflow.domain.boxes.Container} has three identical {@link com.codeflow.domain.boxes.Dimension},
 * it has only one {@link Orientation}. In general, we have 1,2 or 6 {@link Orientation}s for 1,2 or 3 unique
 * {@link com.codeflow.domain.boxes.Dimension}s, respectively. In each {@link LayerIteration}, each {@link Orientation}
 * of the {@link com.codeflow.domain.boxes.Container} is packed once for each {@link Layer}.
 * <p>
 * Each {@link LayerIteration} begins packing with an initial {@link Layer} thickness taken from {@link Layer#dimension} value.
 * Thus, if we have 7 different {@link com.codeflow.domain.boxes.Dimension} values in our {@link java.util.List}s of {@link Layer} and
 * the {@link com.codeflow.domain.boxes.Container} has 3 unique {@link com.codeflow.domain.boxes.Dimension}s, the
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


    public LayerIteration(Layer layer,
                          TopologyService topologyService,
                          ActionService actionService) {
        this.layer = layer;
        this.layerThickness = layer.getDimension();
        this.topologyService = topologyService;
        this.actionService = actionService;
    }

    public void run(OrientationIteration orientationIteration) {
        packing = true;
        do {
            layerinlayer = 0.;
            layerDone = false;

            PackLayer();

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
        } while (packing);
    }

    private void PackLayer() {
        double lenx;
        double lenz;
        double lpz;

        if (layerThickness == 0) {
            packing = false;
            return;
        }


        for (; ; ) {
            Corner cornerWithSmallestLength = topologyService.findCornerWithSmallestLength();
            Situation topologySituation = topologyService.analyzeTopology(cornerWithSmallestLength);
            actionService.pack(topologySituation);
//            VolumeCheck();
        }
    }


}
