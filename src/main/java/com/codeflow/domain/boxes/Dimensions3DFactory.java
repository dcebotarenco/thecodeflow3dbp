package com.codeflow.domain.boxes;

import java.util.HashMap;
import java.util.HashSet;

public class Dimensions3DFactory {

    Dimensions3D create(Double width, Double height, Double length) {
        HashMap<Dimension, Double> dimensions = new HashMap<>();
        dimensions.put(Dimension.WIDTH, width);
        dimensions.put(Dimension.HEIGHT, height);
        dimensions.put(Dimension.LENGTH, length);
        return new Dimensions3D(dimensions, new HashSet<>(dimensions.values()));
    }
}
