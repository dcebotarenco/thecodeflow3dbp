package com.codeflow.domain.boxes;

import java.util.List;

/**
 * Container in what the Articles have to be packed.
 */
public class Container extends Box3D {

    Container(Long id, Dimensions3D dimensions3D, List<Orientation> orientations) {
        super(id, dimensions3D, orientations);
    }

    @Override
    public String toString() {
        return "Container{" + super.toString() + "}";
    }
}
