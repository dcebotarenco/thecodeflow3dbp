package com.codeflow.domain.box;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeFactory;
import com.codeflow.domain.dimension.Dimensions;
import com.codeflow.domain.dimension.DimensionsFactory;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationService;

import java.util.List;
import java.util.Random;

class Box3DFactoryImpl implements Box3DFactory<Box3D> {

    private BoxTypeFactory boxTypeFactory;
    private DimensionsFactory dimensions3DFactory;
    private OrientationService<Orientation> orientationService;

    Box3DFactoryImpl(BoxTypeFactory boxTypeFactory, DimensionsFactory dimensions3DFactory, OrientationService<Orientation> orientationService) {
        this.boxTypeFactory = boxTypeFactory;
        this.dimensions3DFactory = dimensions3DFactory;
        this.orientationService = orientationService;
    }

    public Box3D create(Long id, Double width, Double height, Double length) {
        Dimensions dimensions = dimensions3DFactory.create(width, height, length);
        List<Orientation> orientations = orientationService.calculateOrientations(dimensions);
        BoxType boxType = boxTypeFactory.create(dimensions, orientations);
        return new Box3DImpl(id, boxType);
    }

    public Box3D create(Double width, Double height, Double length) {
        Dimensions dimensions = dimensions3DFactory.create(width, height, length);
        List<Orientation> orientations = orientationService.calculateOrientations(dimensions);
        BoxType boxType = boxTypeFactory.create(dimensions, orientations);
        Random random = new Random();
        long id = random.nextInt(100);
        return new Box3DImpl(id, boxType);
    }

}
