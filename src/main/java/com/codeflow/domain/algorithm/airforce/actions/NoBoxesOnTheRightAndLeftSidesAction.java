package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.BestFitInRequired;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.container.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapFactory;

public class NoBoxesOnTheRightAndLeftSidesAction extends AbstractAction implements Action {


    private final PackingService packingService;

    NoBoxesOnTheRightAndLeftSidesAction(SearchingService searchingService,
                                        GapFactory gapFactory,
                                        PackingService packingService) {
        super(searchingService, gapFactory);
        this.packingService = packingService;
    }

    @Override
    public void act(Corner cornerWithSmallestLength,
                    ContainerOrientation containerOrientation,
                    Layer layer) {

        double maxAndRequiredLength = containerOrientation.getRemainLength() - cornerWithSmallestLength.getLength();
        double requiredWidth = cornerWithSmallestLength.getWidth();
        Gap requiredGapImpl = getGapFactoryImpl().create(requiredWidth, layer.getDimension(), maxAndRequiredLength);
        Gap maxGapImpl = getGapFactoryImpl().create(requiredWidth, containerOrientation.getRemainHeight(), maxAndRequiredLength);
        SearchResult searchResult = getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);

        if (searchResult.getBestFitInRequired().isPresent()) {
            BestFitInRequired bestFitInRequired = searchResult.getBestFitInRequired().get();
            packingService.pack(containerOrientation, bestFitInRequired.getOrientation(), bestFitInRequired.getPosition());
        } else if (searchResult.getBestFitBiggerThenRequired().isPresent()) {

        } else {
            layer.done();
        }

//                CheckFound();
//
//                if (layerDone) {
//                    //System.out.println("S1 layer done");
//                    break;
//                }
//                if (evened) {
//                    //System.out.println("S1 layer evened");
//                    continue;
//                }
//
//                itemsToPack.get(cboxi).CoordX = 0;
//                itemsToPack.get(cboxi).CoordY = packedy;
//                itemsToPack.get(cboxi).CoordZ = smallestZ.CumZ;
//                if (cboxx == smallestZ.CumX) {
//                    smallestZ.CumZ = smallestZ.CumZ + cboxz;
//                } else {
//                    smallestZ.Post = new ScrapPad();
//
//                    smallestZ.Post.Post = null;
//                    smallestZ.Post.Pre = smallestZ;
//                    smallestZ.Post.CumX = smallestZ.CumX;
//                    smallestZ.Post.CumZ = smallestZ.CumZ;
//                    smallestZ.CumX = cboxx;
//                    smallestZ.CumZ = smallestZ.CumZ + cboxz;
//                }
    }
}
