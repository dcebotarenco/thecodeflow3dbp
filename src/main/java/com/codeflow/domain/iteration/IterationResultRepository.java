package com.codeflow.domain.iteration;

import java.util.HashMap;
import java.util.Map;

public class IterationResultRepository {

    private Map<Long, Map<Long, IterationResult>> variantResultMap;

    public IterationResultRepository() {
        this.variantResultMap = new HashMap<>();
    }

    public void save(Long orientation, Long layer, IterationResult result) {
        if (variantResultMap.get(orientation) == null) {
            variantResultMap.put(orientation, new HashMap<>());
            Map<Long, IterationResult> layerVariantResultMap = variantResultMap.get(orientation);
            layerVariantResultMap.put(layer, result);
        } else {
            Map<Long, IterationResult> layerVariantResultMap = variantResultMap.get(orientation);
            layerVariantResultMap.put(layer, result);
        }

    }

    public IterationResult findByResult(Long orientation, Long layer) {
        return variantResultMap.get(orientation).get(layer);
    }
}
