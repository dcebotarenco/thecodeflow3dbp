package com.codeflow.domain.algorithm.airforce.topology;

import org.junit.Assert;
import org.junit.Test;

public class TopViewTopologyTest {

    @Test
    public void findWithSmallestLength() {
        Corner firstCorner = new Corner(100D, 0D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        Corner withSmallestLength = topViewTopology.findWithSmallestLength();
        Assert.assertEquals(firstCorner, withSmallestLength);
        topViewTopology.addLast(new Corner(50D, 1D));
        Corner withSmallestLength1 = topViewTopology.findWithSmallestLength();
        Assert.assertEquals(firstCorner, withSmallestLength1);
    }

    @Test
    public void appendCorner() {
        Corner firstCorner = new Corner(100., 0.);
        Corner toAppend = new Corner(50D, 1D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        topViewTopology.addLast(toAppend);
        Assert.assertEquals(2, topViewTopology.getRightCorners().size());
        Assert.assertEquals(firstCorner, topViewTopology.getRightCorners().get(0));
        Assert.assertEquals(toAppend, topViewTopology.getRightCorners().get(1));
    }


    @Test
    public void hasCornerOnLeft() {
        Corner firstCorner = new Corner(100D, 0D);
        Corner toAppend = new Corner(50D, 1D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        Assert.assertFalse(topViewTopology.hasCornerOnLeft(firstCorner));
        topViewTopology.addFirst(toAppend);
        Assert.assertTrue(topViewTopology.hasCornerOnLeft(firstCorner));

    }

    @Test
    public void hasCornerOnRight() {
        Corner firstCorner = new Corner(100D, 0D);
        Corner toAppend = new Corner(50D, 1D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        Assert.assertFalse(topViewTopology.hasCornerOnRight(firstCorner));
        topViewTopology.addLast(toAppend);
        Assert.assertTrue(topViewTopology.hasCornerOnRight(firstCorner));
    }

    @Test
    public void getRightCorner() {
        Corner firstCorner = new Corner(100D, 0D);
        Corner toAppend = new Corner(50D, 1D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        topViewTopology.addLast(toAppend);
        Corner rightCorner = topViewTopology.getRightCorner(firstCorner);
        Assert.assertEquals(toAppend, rightCorner);
    }

    @Test
    public void getLeftCorner() {
        Corner firstCorner = new Corner(100D, 0D);
        Corner toAppend = new Corner(50D, 1D);
        TopViewTopology topViewTopology = new TopViewTopology(firstCorner);
        topViewTopology.addLast(toAppend);
        Corner leftCorner = topViewTopology.getLeftCorner(toAppend);
        Assert.assertEquals(firstCorner, leftCorner);
    }
}