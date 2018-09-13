package com.codeflow.domain.box;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.orientation.Orientation;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OrientationServiceTest extends SharedTest {

    @Test
    public void calculateOrientations3Unique() {
        List<Orientation> orientations = config.getOrientationService().calculateOrientations(config.getDimensionsFactory().create(2., 3., 4.));
        Assert.assertEquals(6, orientations.size());
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(2., 3., 4.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(2., 4., 3.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(4., 3., 2.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(3., 2., 4.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(3., 4., 2.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(4., 2., 3.)));
    }

    @Test
    public void calculateOrientations2Unique() {
        List<Orientation> orientations = config.getOrientationService().calculateOrientations(config.getDimensionsFactory().create(2., 2., 4.));
        Assert.assertEquals(3, orientations.size());
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(2., 2., 4.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(2., 4., 2.)));
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(4., 2., 2.)));
    }

    @Test
    public void calculateOrientations1Unique() {
        List<Orientation> orientations = config.getOrientationService().calculateOrientations(config.getDimensionsFactory().create(2., 2., 2.));
        Assert.assertEquals(1, orientations.size());
        Assert.assertTrue(orientations.contains(config.getOrientationFactory().create(2., 2., 2.)));
    }
}