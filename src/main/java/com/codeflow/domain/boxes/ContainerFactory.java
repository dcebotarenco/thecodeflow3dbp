package com.codeflow.domain.boxes;

import com.codeflow.domain.orientation.Orientation;

import java.util.List;
import java.util.Set;

public class ContainerFactory extends Box3DFactory<Container> {
    private OrientationService orientationService;

    public ContainerFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory);
        this.orientationService = orientationService;
    }


    Container create(Long id, Dimensions3D dimensions3D) {
        Set<Orientation> orientations = orientationService.calculateOrientations(dimensions3D);
        return new Container(id, dimensions3D, orientations);
    }
}
