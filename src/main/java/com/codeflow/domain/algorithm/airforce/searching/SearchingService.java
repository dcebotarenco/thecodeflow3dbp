package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.boxes.BoxType;
import com.codeflow.domain.boxes.BoxTypeRepository;
import com.codeflow.domain.boxes.Gap;
import com.codeflow.domain.boxes.Orientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchingService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SearchingService.class);

    private BoxTypeRepository boxTypeRepository;

    public SearchingService(BoxTypeRepository boxTypeRepository) {
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

    SearchResult findBoxTypes(Gap requiredGap, Gap maxGap) {
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
                .sorted(Comparator.comparingDouble((Orientation o) -> {
                    return Math.abs(o.getHeight() - requiredGap.getHeight());
                })
                        .thenComparingDouble((Orientation o) -> {
                            return Math.abs(o.getWidth() - requiredGap.getWidth());
                        })
                        .thenComparingDouble((Orientation o) -> {
                            return Math.abs(requiredGap.getLength() - o.getLength());
                        })).collect(Collectors.toList());

//        List<Orientation> sortedBiggerThenRequiredGapHeight = biggerThenRequiredGapHeight.stream()
//                .sorted((o1, o2) -> {
//                    if (o1.getHeight() - requiredGap.getHeight() < o2.getHeight() - requiredGap.getHeight()) {
//                        return -1;
//                    } else if (o1.getHeight() - requiredGap.getHeight() == o2.getHeight() - requiredGap.getHeight()) {
//                        if (o1.getWidth() - requiredGap.getWidth() < o2.getWidth() - requiredGap.getWidth()) {
//                            return -1;
//                        } else if (o1.getWidth() - requiredGap.getWidth() == o2.getWidth() - requiredGap.getWidth()) {
//                            if (Math.abs(requiredGap.getLength() - o1.getLength()) < Math.abs(requiredGap.getLength() - o2.getLength())) {
//                                return -1;
//                            } else {
//                                return 0;
//                            }
//                        } else {
//                            return 0;
//                        }
//                    }else {
//                        return 0;
//                    }
//                }).collect(Collectors.toList());
//        Double previousHeightDiff = 0.;
//        Double previousWidthDiff = 0.;
//        Double previousAbsLengthDiff = 0.;
//        Integer oIndex = 0;
//        for (int i = 0; i < biggerThenRequiredGapHeight.size(); i++) {
//            if (i == 0) {
//                previousHeightDiff = biggerThenRequiredGapHeight.get(i).getHeight() - requiredGap.getHeight();
//                previousWidthDiff = biggerThenRequiredGapHeight.get(i).getWidth() - requiredGap.getWidth();
//                previousHeightDiff = Math.abs(requiredGap.getLength() - biggerThenRequiredGapHeight.get(i).getLength());
//                oIndex = i;
//            } else {
//                Double nextHeightDiff = biggerThenRequiredGapHeight.get(i).getHeight() - requiredGap.getHeight();
//                Double nextWidthDiff = biggerThenRequiredGapHeight.get(i).getWidth() - requiredGap.getWidth();
//                Double nextAbsLengthDiff = Math.abs(requiredGap.getLength() - biggerThenRequiredGapHeight.get(i).getLength());
//                if (nextHeightDiff < previousHeightDiff) {
//                    oIndex = i;
//                    previousHeightDiff = nextHeightDiff;
//                    previousWidthDiff = nextWidthDiff;
//                    previousAbsLengthDiff = nextAbsLengthDiff;
//                } else if (nextHeightDiff.equals(previousHeightDiff) && nextWidthDiff < previousWidthDiff) {
//                    oIndex = i;
//                    previousHeightDiff = nextHeightDiff;
//                    previousWidthDiff = nextWidthDiff;
//                    previousAbsLengthDiff = nextAbsLengthDiff;
//                } else if (nextHeightDiff.equals(previousHeightDiff) && nextWidthDiff.equals(previousWidthDiff) && nextAbsLengthDiff < previousAbsLengthDiff) {
//                    oIndex = i;
//                    previousHeightDiff = nextHeightDiff;
//                    previousWidthDiff = nextWidthDiff;
//                    previousAbsLengthDiff = nextAbsLengthDiff;
//                }
//            }
//        }


//        Orientation biggerThenRequiredGap = biggerThenRequiredGapHeight.get(oIndex);
        Orientation biggerThenRequiredGap = sortedBiggerThenRequiredGapHeight.get(0);
        Position biggerThenRequiredGapPosition = calculatePositionForBiggerThenHeight(requiredGap, biggerThenRequiredGap);
        searchResult.addBestFitBiggerThenRequired(biggerThenRequiredGap, biggerThenRequiredGapPosition);
    }

    private void searchSmallerAndClosestToRequiredHeight(Gap requiredGap, SearchResult searchResult, List<Orientation> smallerThenRequiredGapHeight) {
        List<Orientation> sortedSmallerThenRequiredGapHeight = smallerThenRequiredGapHeight.stream()
                .sorted(Comparator.comparingDouble((Orientation o) ->  Math.abs(requiredGap.getHeight() - o.getHeight()))
                        .thenComparingDouble((Orientation o) ->  Math.abs(requiredGap.getWidth() - o.getWidth()))
                        .thenComparingDouble((Orientation o) -> Math.abs(requiredGap.getLength() - o.getLength())))
                .collect(Collectors.toList());
        Orientation bestFitInRequiredGap = sortedSmallerThenRequiredGapHeight.get(0);
        Position bestFitPosition = calculatePositionForSmallerThenHeight(requiredGap, bestFitInRequiredGap);
        searchResult.addBestFitInRequired(bestFitInRequiredGap, bestFitPosition);
    }

    private Position calculatePositionForSmallerThenHeight(Gap gap, Orientation orientation) {
        double x = gap.getWidth() - orientation.getWidth();
        double y = gap.getHeight() - orientation.getHeight();
        double z = Math.abs(gap.getLength() - orientation.getLength());
        return new Position(x, y, z);
    }

    private Position calculatePositionForBiggerThenHeight(Gap gap, Orientation orientation) {
        double x = gap.getWidth() - orientation.getWidth();
        double y = orientation.getHeight() - gap.getHeight();
        double z = Math.abs(gap.getLength() - orientation.getLength());
        return new Position(x, y, z);
    }

}
