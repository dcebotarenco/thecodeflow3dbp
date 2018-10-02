package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;

public class NoBoxesOnTheLeftAction extends AbstractAction implements Action {

    private final PackingService packingService;

    NoBoxesOnTheLeftAction(SearchingService searchingService,
                           PackingService packingService) {
        super(searchingService);
        this.packingService = packingService;
    }

    @Override
    public void act(Corner cornerWithSmallestLength, ContainerOrientation containerOrientation, Layer layer) {
//        double requiredWidth = cornerWithSmallestLength.getWidth();
//        double maxLength = containerOrientation.getRemainLength() - cornerWithSmallestLength.getLength();
//        Corner cornerFromRight = topologyService.getCornerFromRight();
//        double requiredLength = cornerFromRight.getLength() - cornerWithSmallestLength.getLength();
//        Gap requiredGapImpl = new GapImpl(requiredWidth, layer.getHeight(), requiredLength);
//        Gap maxGapImpl = new GapImpl(requiredWidth, containerOrientation.getRemainHeight(), maxLength);
//        SearchResult searchResult = getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);
//
//
//        ArticleOrientation article;
//        if (searchResult.getBestFitInRequired().isPresent()) {
//            article = searchResult.getBestFitInRequired().get();
//        } else if (searchResult.getBestFitBiggerThenRequired().isPresent()) {
//            article = searchResult.getBestFitBiggerThenRequired().get();
////            layer.addLayerInLayer(layerFactory.create(article.getHeight() - layer.getHeight(),
////                    cornerWithSmallestLength.getLength(), null));
//
//        } else {
//            layer.even();
//            return;
//        }
//
//        Position position;
//        Corner rightCorner = topologyService.getCornerFromRight();
//        if (article.getWidth().equals(cornerWithSmallestLength.getWidth())) {
//            position = positionFactory.create(0D, containerOrientation.getPackedHeight(), cornerWithSmallestLength.getLength());
//            if (cornerWithSmallestLength.getLength() + article.getLength() == rightCorner.getLength()) {
//                topologyService.addCornerBeforeSmallest(
////                        cornerFactory.create(rightCorner.getWidth(), rightCorner.getLength()));
//            } else {
//                topologyService.updateLength(cornerWithSmallestLength, cornerWithSmallestLength.getLength() + article.getLength());
//            }
//        } else {
//            position = positionFactory.create(cornerWithSmallestLength.getWidth() - article.getWidth(),
//                    containerOrientation.getPackedHeight(), cornerWithSmallestLength.getLength());
//            if (cornerWithSmallestLength.getLength() + article.getLength() == rightCorner.getLength()) {
//                topologyService.updateWidth(cornerWithSmallestLength, cornerWithSmallestLength.getWidth() - article.getWidth());
//            } else {
//
//            }
//        }
//
//        packingService.pack(containerOrientation, article, position);
    }
}
