package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerFactory;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.TopologyService;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerFactory;
import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.container.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionFactory;

public class NoBoxesOnTheRightAndLeftSidesAction extends AbstractAction implements Action {


    private final PackingService packingService;
    private PositionFactory<Position> positionFactory;
    private final TopologyService topologyService;
    private final CornerFactory<Corner> cornerFactory;
    private final LayerFactory<Layer> layerFactory;

    NoBoxesOnTheRightAndLeftSidesAction(SearchingService searchingService,
                                        GapFactory gapFactory,
                                        PackingService packingService,
                                        PositionFactory<Position> positionFactory,
                                        TopologyService topologyService,
                                        CornerFactory<Corner> cornerFactory,
                                        LayerFactory<Layer> layerFactory) {
        super(searchingService, gapFactory);
        this.packingService = packingService;
        this.positionFactory = positionFactory;
        this.topologyService = topologyService;
        this.cornerFactory = cornerFactory;
        this.layerFactory = layerFactory;
    }

    @Override
    public void act(Corner cornerWithSmallestLength,
                    ContainerOrientation containerOrientation,
                    Layer layer) {

        double maxAndRequiredLength = containerOrientation.getRemainLength() - cornerWithSmallestLength.getLength();
        double requiredWidth = cornerWithSmallestLength.getWidth();
        Gap requiredGapImpl = getGapFactoryImpl().create(requiredWidth, layer.getHeight(), maxAndRequiredLength);
        Gap maxGapImpl = getGapFactoryImpl().create(requiredWidth, containerOrientation.getRemainHeight(), maxAndRequiredLength);
        SearchResult searchResult = getSearchingService().findBoxTypes(requiredGapImpl, maxGapImpl);

        Position position = positionFactory.create(0D, containerOrientation.getPackedHeight(), cornerWithSmallestLength.getLength());

        if (searchResult.getBestFitInRequired().isPresent()) {
            ArticleOrientation articleOrientation = searchResult.getBestFitInRequired().get();
            packingService.pack(containerOrientation, articleOrientation, position);
            if (cornerWithSmallestLength.getWidth().equals(articleOrientation.getWidth())) {
                topologyService.updateSmallestCorner(
                        cornerFactory.create(cornerWithSmallestLength.getWidth(),
                                cornerWithSmallestLength.getLength() + articleOrientation.getLength()));
            } else {
                topologyService.addCornerBefore(cornerFactory.create(articleOrientation.getWidth(), articleOrientation.getLength()));
            }
        } else if (searchResult.getBestFitBiggerThenRequired().isPresent()) {
            ArticleOrientation articleOrientation = searchResult.getBestFitBiggerThenRequired().get();
            packingService.pack(containerOrientation, articleOrientation, position);
//            layer.addLayerInLayer(layerFactory.create());
//            layer.addLayerInLayer(articleOrientation.getHeight() - layer.getDimension());

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
