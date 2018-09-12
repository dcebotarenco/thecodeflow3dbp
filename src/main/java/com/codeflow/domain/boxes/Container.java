package com.codeflow.domain.boxes;

/**
 * Container in what the Articles have to be packed.
 */
public class Container extends Box3D {

    Container(Long id, BoxType boxType) {
        super(id, boxType);
    }

    @Override
    public String toString() {
        return "Container{" + super.toString() + "}";
    }
}
