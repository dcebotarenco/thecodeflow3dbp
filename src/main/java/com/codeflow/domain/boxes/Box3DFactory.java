package com.codeflow.domain.boxes;

import java.util.List;

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
        BoxType boxType = new BoxType(dimensions3D, orientations);
        return create(id, boxType);
    }

    abstract E create(Long id, BoxType boxType);
}
