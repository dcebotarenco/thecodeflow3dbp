package com.codeflow.domain.boxes;

public abstract class Box3DFactory<E extends Box3D> {

    private Dimensions3DFactory dimensions3DFactory;

    Box3DFactory(Dimensions3DFactory dimensions3DFactory) {
        this.dimensions3DFactory = dimensions3DFactory;
    }

    public E create(Long id, Double width, Double height, Double length) {
        Dimensions3D dimensions3D = dimensions3DFactory.create(width, height, length);
        return create(id, dimensions3D);
    }

    abstract E create(Long id, Dimensions3D dimensions3D);
}
