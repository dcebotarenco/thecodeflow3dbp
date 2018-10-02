package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionImpl;

public class NoBoxesOnTheRightAndLeftSidesAction extends AbstractAction implements Action {


    private final PackingService packingService;

    NoBoxesOnTheRightAndLeftSidesAction(SearchingService searchingService,
                                        PackingService packingService) {
        super(searchingService);
        this.packingService = packingService;
    }

    @Override
    public void act(Corner cornerWithSmallestLength,
                    ContainerOrientation containerOrientation,
                    Layer layer) {
        double maxAndRequiredLength = containerOrientation.getRemainLength() - cornerWithSmallestLength.getLength();
        double requiredWidth = cornerWithSmallestLength.getWidth();
        Gap requiredGapImpl = new GapImpl(requiredWidth, layer.getHeight(), maxAndRequiredLength);
        Gap maxGapImpl = new GapImpl(requiredWidth, containerOrientation.getRemainHeight(), maxAndRequiredLength);
        SearchResult searchResult = getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);


        ArticleOrientation article;
        Position position = new PositionImpl(0D, containerOrientation.getPackedHeight(), cornerWithSmallestLength.getLength());

        if (searchResult.getBestFitInRequired().isPresent()) {
            article = searchResult.getBestFitInRequired().get();
        } else if (searchResult.getBestFitBiggerThenRequired().isPresent()) {
            article = searchResult.getBestFitBiggerThenRequired().get();
//            registerLayerInLayer();
            layer.increaseLayerThickness(article.getHeight());
        } else {
            layer.done();
            return;
        }

        // Update corners
        if (cornerWithSmallestLength.getWidth().equals(article.getWidth())) {
//            topologyService.updateLength(cornerWithSmallestLength, cornerWithSmallestLength.getLength() + article.getLength());
        } else {
//            topologyService.addCornerBeforeSmallest(cornerFactory.create(article.getWidth(), article.getLength()));
        }


        packingService.pack(containerOrientation, article, position);


    }
}
