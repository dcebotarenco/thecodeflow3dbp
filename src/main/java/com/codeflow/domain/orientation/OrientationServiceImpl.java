package com.codeflow.domain.orientation;

import com.codeflow.domain.dimension.Dimensions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OrientationServiceImpl implements OrientationService<Orientation> {

    private OrientationFactory orientationFactory;

    OrientationServiceImpl(OrientationFactory orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    @Override
    public List<Orientation> calculateOrientations(Dimensions dimensions) {
        Set<Orientation> orientations = new HashSet<>();
        /*As it is*/
        orientations.add(orientationFactory.create(dimensions.getWidth(), dimensions.getHeight(), dimensions.getLength()));
        /*Rotate on X*/
        orientations.add(orientationFactory.create(dimensions.getWidth(), dimensions.getLength(), dimensions.getHeight()));
        /*Rotate on Y*/
        orientations.add(orientationFactory.create(dimensions.getLength(), dimensions.getHeight(), dimensions.getWidth()));
        /*Rotate on Z*/
        orientations.add(orientationFactory.create(dimensions.getHeight(), dimensions.getWidth(), dimensions.getLength()));
        /*Rotate on X&Y*/
        orientations.add(orientationFactory.create(dimensions.getHeight(), dimensions.getLength(), dimensions.getWidth()));
        /*Rotate on Z&Y*/
        orientations.add(orientationFactory.create(dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight()));
        return new ArrayList<>(orientations);
    }
}
