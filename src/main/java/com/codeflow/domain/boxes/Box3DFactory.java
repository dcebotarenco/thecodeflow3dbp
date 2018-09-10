package com.codeflow.domain.boxes;

import java.util.List;
import java.util.Set;

public abstract class Box3DFactory<E extends Box3D> {

    private Dimensions3DFactory dimensions3DFactory;
    private OrientationService orientationService;

    Box3DFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        this.dimensions3DFactory = dimensions3DFactory;
        this.orientationService = orientationService;
    }

    public E create(Long id, Double width, Double height, Double length) {
        Dimensions3D dimensions3D = dimensions3DFactory.create(width, height, length);
        List<Orientation> orientations = orientationService.calculateOrientations(dimensions3D);
        return create(id, dimensions3D, orientations);
    }

    abstract E create(Long id, Dimensions3D dimensions3D, List<Orientation> orientations);
}
