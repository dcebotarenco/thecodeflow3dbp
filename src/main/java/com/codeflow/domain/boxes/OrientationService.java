package com.codeflow.domain.boxes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrientationService {

    private OrientationFactory orientationFactory;

    public OrientationService(OrientationFactory orientationFactory) {
        this.orientationFactory = orientationFactory;
    }

    List<Orientation> calculateOrientations(Dimensions3D dimensions3D) {
        List<Orientation> orientations = new ArrayList<>();
        /*As it is*/
        orientations.add(orientationFactory.create(dimensions3D.getWidth(), dimensions3D.getHeight(), dimensions3D.getLength()));
        /*Rotate on X*/
        orientations.add(orientationFactory.create(dimensions3D.getWidth(), dimensions3D.getLength(), dimensions3D.getHeight()));
        /*Rotate on Y*/
        orientations.add(orientationFactory.create(dimensions3D.getLength(), dimensions3D.getHeight(), dimensions3D.getWidth()));
        /*Rotate on Z*/
        orientations.add(orientationFactory.create(dimensions3D.getHeight(), dimensions3D.getWidth(), dimensions3D.getLength()));
        /*Rotate on X&Y*/
        orientations.add(orientationFactory.create(dimensions3D.getHeight(), dimensions3D.getLength(), dimensions3D.getWidth()));
        /*Rotate on Z&Y*/
        orientations.add(orientationFactory.create(dimensions3D.getLength(), dimensions3D.getWidth(), dimensions3D.getHeight()));
        return orientations;
    }
}
