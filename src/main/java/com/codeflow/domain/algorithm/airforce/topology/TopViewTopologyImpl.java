package com.codeflow.domain.algorithm.airforce.topology;

import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


/**
 * We perform layer packing or wall building thus reducing the problem to a systematic series of two-dimensional
 * packing problems. We act along the x-axis/width and z-axis/length. To track the current topology,
 * each right {@link Corner} coordinate data is maintained in a doubly linked list. As {@link com.codeflow.domain.articletype.ArticleType} are packed,
 * this coordinate data will change. The doubly linked list facilitates the change to the coordinate data.
 * This approach means we only need to track the current edge being packed, and we avoid overlaps of layers and
 * {@link com.codeflow.domain.containertype.ContainerType} edges. Each packing action begins by finding the smallest {@link Corner}
 * with length-value in the {@link java.util.List} list and from that list finding the current gap in the layer to fill.
 */
public class TopViewTopologyImpl implements TopViewTopology {

    public static final Logger LOGGER = LoggerFactory.getLogger(TopViewTopologyImpl.class);

    private LinkedList<Corner> rightCorners;

    public TopViewTopologyImpl(Corner firstCorner) {
        rightCorners = new LinkedList<>();
        addLast(firstCorner);
        firstCorner.setTopology(this);
    }

    @Override
    public Corner findWithSmallestLength() {
        LinkedList<Corner> rightCorners = new LinkedList<>(this.rightCorners);
        rightCorners.sort(Comparator.comparingDouble(Corner::getLength));
        String collect = this.rightCorners.stream().map(c -> c.toString()).collect(Collectors.joining(","));
        LOGGER.info(collect);
        return rightCorners.getFirst();
    }

    @Override
    public void remove(Corner corner) {
        rightCorners.remove(corner);
    }

    @Override
    public void removeAndAllOnRight(Corner smallestZ) {
        int i = rightCorners.indexOf(smallestZ);
        for (int index = i; index < rightCorners.size(); index++) {
            rightCorners.remove(index);
        }
    }

    @Override
    public List<Corner> getRightCorners() {
        return Collections.unmodifiableList(rightCorners);
    }

    @Override
    public void addLast(Corner corner) {
        rightCorners.addLast(corner);
    }

    @Override
    public void addFirst(Corner corner) {
        rightCorners.addFirst(corner);
    }

    @Override
    public void addAfter(Corner target, Corner toAdd) {
        int i = rightCorners.indexOf(target);
        rightCorners.add(++i, toAdd);
        toAdd.setTopology(this);
    }

    @Override
    public void addBefore(Corner target, Corner toAdd) {
        int i = rightCorners.indexOf(target);
        rightCorners.add(i, toAdd);
        toAdd.setTopology(this);
    }


    @Override
    public boolean hasCornerOnLeft(Corner corner) {
        int i = rightCorners.indexOf(corner);
        ListIterator<Corner> cornerListIterator = rightCorners.listIterator(i);
        return cornerListIterator.hasPrevious();
    }

    @Override
    public boolean hasCornerOnRight(Corner corner) {
        int i = rightCorners.indexOf(corner);
        ListIterator<Corner> cornerListIterator = rightCorners.listIterator(++i);
        return cornerListIterator.hasNext();
    }

    @Override
    public String toString() {
        return "TopViewTopology{" +
                "rightCorners=" + rightCorners +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TopViewTopologyImpl that = (TopViewTopologyImpl) o;

        return new EqualsBuilder()
                .append(rightCorners, that.rightCorners)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rightCorners)
                .toHashCode();
    }

    @Override
    public Corner getRightCorner(Corner corner) {
        int i = rightCorners.indexOf(corner);
        ListIterator<Corner> cornerListIterator = rightCorners.listIterator(++i);
        return cornerListIterator.next();
    }

    @Override
    public Corner getLeftCorner(Corner corner) {
        int i = rightCorners.indexOf(corner);
        ListIterator<Corner> cornerListIterator = rightCorners.listIterator(i);
        return cornerListIterator.previous();
    }
}
