package com.codeflow.domain.boxes;

public class ContainerFactory extends Box3DFactory<Container> {

    public ContainerFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory, orientationService);
    }


    Container create(Long id, BoxType boxType) {
        return new Container(id, boxType);
    }
}
