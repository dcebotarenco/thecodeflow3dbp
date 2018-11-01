package com.codeflow.domain.algorithm.airforce;

public class PackLayerAttemptOutput {
    public PackLayerAttemptInput input;
    public double packedVolume;
    public boolean packing;

    public PackLayerAttemptOutput(PackLayerAttemptInput input) {
        this.input = input;
    }
}
