package com.codeflow.domain.algorithm.airforce;

public class PackLayerAttemptResult {
    public PackLayerAttemptInput input;
    public double packedVolume;
    public Double foundArticleHeightBiggerThenRequired;
    public double prelayer;
    public double lilz;
    public double layerinlayer;

    public PackLayerAttemptResult(PackLayerAttemptInput input) {
        this.layerinlayer = 0;
        this.input = input;
        this.foundArticleHeightBiggerThenRequired = input.layerThickness;
    }
}
