package com.codeflow.domain.dimension;

import java.util.HashMap;
import java.util.HashSet;

class DimensionsFactoryImpl implements DimensionsFactory<Dimensions> {

    public Dimensions create(Double width, Double height, Double length) {
        HashMap<Dimension, Double> dimensions = new HashMap<>();
        dimensions.put(Dimension.WIDTH, width);
        dimensions.put(Dimension.HEIGHT, height);
        dimensions.put(Dimension.LENGTH, length);
        return new DimensionsImpl(dimensions, new HashSet<>(dimensions.values()));
    }
}
