package com.codeflow.domain.boxes;

public class GapFactory extends Box3DFactory<Gap> {
    public GapFactory(Dimensions3DFactory dimensions3DFactory, OrientationService orientationService) {
        super(dimensions3DFactory, orientationService);
    }

    @Override
    Gap create(Long id, BoxType boxType) {
        return new Gap(id, boxType);
    }
}
