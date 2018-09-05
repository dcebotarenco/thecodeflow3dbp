package com.codeflow.domain.boxes;

import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.orientation.OrientationFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class OrientationServiceTest {

    @Test
    public void calculateOrientations3Unique() {
        Dimensions3DFactory dimensions3DFactory = new Dimensions3DFactory();
        OrientationFactory orientationFactory = new OrientationFactory(dimensions3DFactory);
        OrientationService service = new OrientationService(orientationFactory);
        Set<Orientation> orientations = service.calculateOrientations(dimensions3DFactory.create(2., 3., 4.));
        Assert.assertEquals(6, orientations.size());
        Assert.assertTrue(orientations.contains(orientationFactory.create(2., 3., 4.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(2., 4., 3.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(4., 3., 2.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(3., 2., 4.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(3., 4., 2.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(4., 2., 3.)));
    }

    @Test
    public void calculateOrientations2Unique() {
        Dimensions3DFactory dimensions3DFactory = new Dimensions3DFactory();
        OrientationFactory orientationFactory = new OrientationFactory(dimensions3DFactory);
        OrientationService service = new OrientationService(orientationFactory);
        Set<Orientation> orientations = service.calculateOrientations(dimensions3DFactory.create(2., 2., 4.));
        Assert.assertEquals(3, orientations.size());
        Assert.assertTrue(orientations.contains(orientationFactory.create(2., 2., 4.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(2., 4., 2.)));
        Assert.assertTrue(orientations.contains(orientationFactory.create(4., 2., 2.)));
    }

    @Test
    public void calculateOrientations1Unique() {
        Dimensions3DFactory dimensions3DFactory = new Dimensions3DFactory();
        OrientationFactory orientationFactory = new OrientationFactory(dimensions3DFactory);
        OrientationService service = new OrientationService(orientationFactory);
        Set<Orientation> orientations = service.calculateOrientations(dimensions3DFactory.create(2., 2., 2.));
        Assert.assertEquals(1, orientations.size());
        Assert.assertTrue(orientations.contains(orientationFactory.create(2., 2., 2.)));
    }
}