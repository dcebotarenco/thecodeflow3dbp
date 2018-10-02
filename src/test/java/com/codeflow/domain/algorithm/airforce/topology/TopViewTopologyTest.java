package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.SharedTest;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerImpl;
import org.junit.Assert;
import org.junit.Test;

public class TopViewTopologyTest extends SharedTest {

    @Test
    public void findWithSmallestLength() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        Corner withSmallestLength = topViewTopology.findWithSmallestLength();
        Assert.assertEquals(firstCorner, withSmallestLength);
        topViewTopology.addLast(new CornerImpl(50D, 1D));
        Corner withSmallestLength1 = topViewTopology.findWithSmallestLength();
        Assert.assertEquals(firstCorner, withSmallestLength1);
    }

    @Test
    public void appendCorner() {
        Corner firstCorner = new CornerImpl(100., 0.);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        topViewTopology.addLast(toAppend);
        Assert.assertEquals(2, topViewTopology.getRightCorners().size());
        Assert.assertEquals(firstCorner, topViewTopology.getRightCorners().get(0));
        Assert.assertEquals(toAppend, topViewTopology.getRightCorners().get(1));
    }


    @Test
    public void hasCornerOnLeft() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        Assert.assertFalse(topViewTopology.hasCornerOnLeft(firstCorner));
        topViewTopology.addFirst(toAppend);
        Assert.assertTrue(topViewTopology.hasCornerOnLeft(firstCorner));

    }

    @Test
    public void hasCornerOnRight() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        Assert.assertFalse(topViewTopology.hasCornerOnRight(firstCorner));
        topViewTopology.addLast(toAppend);
        Assert.assertTrue(topViewTopology.hasCornerOnRight(firstCorner));
    }

    @Test
    public void getRightCorner() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        topViewTopology.addLast(toAppend);
        Corner rightCorner = topViewTopology.getRightCorner(firstCorner);
        Assert.assertEquals(toAppend, rightCorner);
    }

    @Test
    public void getLeftCorner() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        topViewTopology.addLast(toAppend);
        Corner leftCorner = topViewTopology.getLeftCorner(toAppend);
        Assert.assertEquals(firstCorner, leftCorner);
    }

    @Test
    public void addAfterCorner() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        topViewTopology.addAfter(firstCorner, toAppend);
        Corner rightCorner = topViewTopology.getRightCorner(firstCorner);
        Assert.assertEquals(toAppend, rightCorner);
    }

    @Test
    public void addBeforeCorner() {
        Corner firstCorner = new CornerImpl(100D, 0D);
        Corner toAppend = new CornerImpl(50D, 1D);
        TopViewTopologyImpl topViewTopology = new TopViewTopologyImpl(firstCorner);
        topViewTopology.addBefore(firstCorner, toAppend);
        Corner leftCorner = topViewTopology.getLeftCorner(firstCorner);
        Assert.assertEquals(toAppend, leftCorner);
    }
}