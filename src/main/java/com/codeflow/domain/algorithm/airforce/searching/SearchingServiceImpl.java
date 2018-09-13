package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.orientation.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class SearchingServiceImpl implements SearchingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchingServiceImpl.class);

    private BoxTypeRepository<BoxType> boxTypeRepository;

    SearchingServiceImpl(BoxTypeRepository<BoxType> boxTypeRepository) {
        this.boxTypeRepository = boxTypeRepository;
    }

    /**
     * Sort in precedence order, a box with a y-dimension closest to Hy
     * but not more than Hmy, with an x-dimension closest to, but not more than Hmx, and a z dimension closest to Hz,
     * but not more than Hmz. This means it considers the y dimension, then among boxes having the same y-dimension,
     * it looks at the x-dimension, and finally among the boxes having the same y- and x-dimension,
     * it looks at the z dimension. It calculates the differences between the gap's dimensions and the box
     * dimensions for each box and picks the box with the least differences to be the best fitting
     * one. It also finds a second box with a y-dimension bigger than the current layer
     * thickness, but closest to the current layer thickness. Boxes that fit and are the proper
     * thickness (y-values) are packed.
     */

    @Override
    public SearchResult findBoxTypes(Gap requiredGap, Gap maxGap) {
        LOGGER.info("Searching box for requiredGap[{}] and maxGap [{}]", requiredGap, maxGap);
        SearchResult searchResult = new SearchResult();
        List<BoxType> boxTypes = boxTypeRepository.boxTypes();
        List<Orientation> allOrientationsOfAllBoxTypes = boxTypes.stream().map(BoxType::getOrientations).flatMap(Collection::stream).collect(Collectors.toList());

        //Filter orientations that does not fit in maximum gap;
        List<Orientation> orientationsFitsMaxGap = allOrientationsOfAllBoxTypes.stream().filter(maxGap::fit).collect(Collectors.toList());

        List<Orientation> smallerThenRequiredGapHeight = orientationsFitsMaxGap.stream().filter(requiredGap::smallerThenHeight).collect(Collectors.toList());
        ArrayList<Orientation> biggerThenRequiredGapHeight = new ArrayList<>(orientationsFitsMaxGap);
        biggerThenRequiredGapHeight.removeAll(smallerThenRequiredGapHeight);

        if (smallerThenRequiredGapHeight.size() > 0) {
            searchSmallerAndClosestToRequiredHeight(requiredGap, searchResult, smallerThenRequiredGapHeight);
        }
        if (biggerThenRequiredGapHeight.size() > 0) {
            searchBiggerAndClosestToRequiredHeight(requiredGap, searchResult, biggerThenRequiredGapHeight);
        }
        LOGGER.info("Search result [{}]", searchResult);
        return searchResult;
    }

    private void searchBiggerAndClosestToRequiredHeight(Gap requiredGap, SearchResult searchResult, ArrayList<Orientation> biggerThenRequiredGapHeight) {
        List<Orientation> sortedBiggerThenRequiredGapHeight = biggerThenRequiredGapHeight.stream()
                .sorted(Comparator.comparingDouble((Orientation o) -> Math.abs(o.getHeight() - requiredGap.getHeight()))
                        .thenComparingDouble((Orientation o) -> Math.abs(o.getWidth() - requiredGap.getWidth()))
                        .thenComparingDouble((Orientation o) -> Math.abs(requiredGap.getLength() - o.getLength()))).collect(Collectors.toList());

        Orientation biggerThenRequiredGap = sortedBiggerThenRequiredGapHeight.get(0);
        Position biggerThenRequiredGapPosition = calculatePositionForBiggerThenHeight(requiredGap, biggerThenRequiredGap);
        searchResult.addBestFitBiggerThenRequired(biggerThenRequiredGap, biggerThenRequiredGapPosition);
    }

    private void searchSmallerAndClosestToRequiredHeight(Gap requiredGap, SearchResult searchResult, List<Orientation> smallerThenRequiredGapHeight) {
        List<Orientation> sortedSmallerThenRequiredGapHeight = smallerThenRequiredGapHeight.stream()
                .sorted(Comparator.comparingDouble((Orientation o) -> Math.abs(requiredGap.getHeight() - o.getHeight()))
                        .thenComparingDouble((Orientation o) -> Math.abs(requiredGap.getWidth() - o.getWidth()))
                        .thenComparingDouble((Orientation o) -> Math.abs(requiredGap.getLength() - o.getLength())))
                .collect(Collectors.toList());
        Orientation bestFitInRequiredGap = sortedSmallerThenRequiredGapHeight.get(0);
        Position bestFitPosition = calculatePositionForSmallerThenHeight(requiredGap, bestFitInRequiredGap);
        searchResult.addBestFitInRequired(bestFitInRequiredGap, bestFitPosition);
    }

    private Position calculatePositionForSmallerThenHeight(Gap gapImpl, Orientation orientation) {
        double x = gapImpl.getWidth() - orientation.getWidth();
        double y = gapImpl.getHeight() - orientation.getHeight();
        double z = Math.abs(gapImpl.getLength() - orientation.getLength());
        return new Position(x, y, z);
    }

    private Position calculatePositionForBiggerThenHeight(Gap gapImpl, Orientation orientation) {
        double x = gapImpl.getWidth() - orientation.getWidth();
        double y = orientation.getHeight() - gapImpl.getHeight();
        double z = Math.abs(gapImpl.getLength() - orientation.getLength());
        return new Position(x, y, z);
    }

}
