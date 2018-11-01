package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;

import java.util.Optional;

public class Iteration {
    private final Layer layer;
    private final ContainerOrientation containerOrientation;
    private final LayerService layerService;
    private IterationSession session;
    public double prepackedy;
    public double preremainpy;

    public Iteration(IterationSession session,
                     Layer layer,
                     ContainerOrientation containerOrientation,
                     LayerService layerService) {
        this.session = session;
        this.layer = layer;
        this.containerOrientation = containerOrientation;
        this.layerService = layerService;
    }

    public IterationSession start() {

        double layerThickness = layer.getHeight();
        double remainpz = session.getRemainpz();
        double remainpy = session.getRemainpy();
        double packedy = session.getPackedy();

        do {
            PackLayerAttemptInput packLayerAttemptInput = new PackLayerAttemptInput(session,
                    layerThickness,
                    remainpz,
                    remainpy,
                    packedy,
                    containerOrientation);
            PackLayerAttempt packLayerAttempt = new PackLayerAttempt(packLayerAttemptInput, session.packedVolume);
            packLayerAttempt.start();
            session.packedVolume = packLayerAttempt.inputVolume;

            packedy = packedy + packLayerAttempt.input.layerThickness;
            remainpy = containerOrientation.getHeight() - packedy;

            if (packLayerAttempt.layerinlayer != 0) {
//                        System.out.println("There is Layer in Layer");
                this.prepackedy = packedy;
                this.preremainpy = remainpy;
                remainpy = packLayerAttempt.input.layerThickness - packLayerAttempt.prelayer;
                packedy = packedy - packLayerAttempt.input.layerThickness + packLayerAttempt.prelayer;
                remainpz = packLayerAttempt.lilz;
                //System.out.println("Assign 3 =" + layerinlayer);
                layerThickness = packLayerAttempt.layerinlayer;
                PackLayerAttemptInput attemptInput = new PackLayerAttemptInput(session, layerThickness, remainpz, remainpy, packedy, containerOrientation);
                PackLayerAttempt layerAttempt = new PackLayerAttempt(attemptInput, session.packedVolume);
                layerAttempt.start();
                session.packedVolume = packLayerAttempt.inputVolume;
//            PackLayer();

                packedy = this.prepackedy;
                remainpy = this.preremainpy;
                remainpz = session.getRemainpz();
            }

            Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, remainpy, session.getRemainingToPack());
            if (!foundLayer.isPresent()) {
                session.packing = false;
                System.out.println(remainpy + "FOUND LAYER:" + 0.);
            } else {
                layerThickness = foundLayer.get().getHeight();
                System.out.println(remainpy + "FOUND LAYER:" + layerThickness);
                if (layerThickness > remainpy) {
                    session.packing = false;
                }
            }

        } while (notStopped());

        this.session.percentageContainerUsed = this.session.packedVolume * 100 / containerOrientation.getVolume();
        return session;
    }

    boolean notStopped() {
        return session.packing;
    }
}
