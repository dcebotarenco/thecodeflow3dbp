package com.codeflow.domain.boxes;

import java.util.List;

public class ContainerFactory extends Box3DFactory<Container> {

    public ContainerFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory, orientationService);
    }


    Container create(Long id, Dimensions3D dimensions3D, List<Orientation> orientations) {
        return new Container(id, dimensions3D, orientations);
    }
}
