package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;

public class NoBoxesOnTheRightAction extends AbstractAction implements Action {
    private final PackingService packingService;

    NoBoxesOnTheRightAction(SearchingService searchingService,
                            PackingService packingService) {
        super(searchingService);
        this.packingService = packingService;
    }

    @Override
    public void act(Corner cornerWithSmallestLength, ContainerOrientation containerOrientation, Layer layer) {
//        Corner cornerFromLeft = topologyService.getCornerFromLeft();
//        double requiredWidth = cornerWithSmallestLength.getWidth() - cornerFromLeft.getWidth();
//        double requiredLength = cornerFromLeft.getLength() - cornerWithSmallestLength.getLength();
//
//        double maxLength = containerOrientation.getRemainLength() - cornerWithSmallestLength.getLength();
//
//        Gap requiredGapImpl = getGapFactoryImpl().create(requiredWidth, layer.getHeight(), requiredLength);
//        Gap maxGapImpl = getGapFactoryImpl().create(requiredWidth, containerOrientation.getRemainHeight(), maxLength);
//        SearchResult searchResult = getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);
//
//        Position position = positionFactory.create(cornerFromLeft.getWidth(), containerOrientation.getPackedHeight(), cornerWithSmallestLength.getLength());
//
//        ArticleOrientation article;
//        if (searchResult.getBestFitInRequired().isPresent()) {
//            article = searchResult.getBestFitInRequired().get();
//        } else if (searchResult.getBestFitBiggerThenRequired().isPresent()) {
//            article = searchResult.getBestFitBiggerThenRequired().get();
//            layer.addLayerInLayer(layerFactory.create(article.getHeight() - layer.getHeight(),
//                    cornerWithSmallestLength.getLength(), null));
//        } else {
//            topologyService.updateWidth(cornerFromLeft, cornerWithSmallestLength.getWidth());
//            topologyService.remove(cornerWithSmallestLength);
//            return;
//        }
//
//        if (article.getWidth().equals(cornerWithSmallestLength.getWidth() - cornerFromLeft.getWidth())) {
//            if ((cornerWithSmallestLength.getLength() + article.getLength()) == (cornerFromLeft.getLength())) {
//                topologyService.updateWidth(cornerFromLeft, cornerWithSmallestLength.getWidth());
//                topologyService.remove(cornerWithSmallestLength);
//            } else {
//                topologyService.updateLength(cornerWithSmallestLength, cornerWithSmallestLength.getLength() + article.getLength());
//            }
//        } else {
//            if ((cornerWithSmallestLength.getLength() + article.getLength()) == (cornerFromLeft.getLength())) {
//                topologyService.updateWidth(cornerFromLeft, cornerFromLeft.getWidth() + article.getWidth());
//            } else {
//                topologyService.addCornerBeforeSmallest(cornerFactory.create(cornerFromLeft.getWidth() + article.getWidth(),
//                        cornerWithSmallestLength.getLength() + article.getLength()));
//            }
//        }
//
//
//        packingService.pack(containerOrientation, article, position);
    }
}
