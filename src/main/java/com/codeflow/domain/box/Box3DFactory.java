package com.codeflow.domain.box;

public interface Box3DFactory<E extends Box3D> {

    E create(Long id, Double width, Double height, Double length);

    E create(Double width, Double height, Double length);

}
