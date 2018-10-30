package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;

public class Iteration {
    public double packedVolume;
    public double packedy;
    public boolean packing;
    public double layerThickness;
    public double remainpy;
    public double remainpz;
    public long packedItemCount;
    public double percentageContainerUsed;
    public long currentIndexOfContainerOrientation;
    public long currentIndexOfLayer;

    public Iteration(Layer layer, ContainerOrientation containerOrientation, long containerOrientationIndex, long currentIndexOfLayer) {
        this.packedVolume = 0;
        this.packedy = 0;
        this.packing = true;
        this.layerThickness = layer.getHeight();
        this.remainpy = containerOrientation.getHeight();
        this.remainpz = containerOrientation.getLength();
        this.packedItemCount = 0;
        this.currentIndexOfContainerOrientation = containerOrientationIndex;
        this.currentIndexOfLayer = currentIndexOfLayer;
    }


}
