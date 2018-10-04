package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.Algorithm;
import com.codeflow.domain.algorithm.Result;
import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyImpl;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerImpl;
import com.codeflow.domain.articletype.ArticleService;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionImpl;

import java.util.List;
import java.util.Optional;

/**
 * Our model packs as many boxes as possible in a given container while selecting the suitable boxes from a given box set.
 * This property makes our approach more realistic. The model is also able to act rectangular boxes in any orientation.
 * Actually, our model not only packs rectangular boxes in any orientation, it also packs according to different orientations of the pallet.
 * In other words, it builds walls or layers along any of the six faces of the given container if all three pallet dimensions are not the same.
 * The model basically builds a new packing during each iteration. Our approach does not limit the number of different boxes in each layer.
 * It may act any number of different boxes within a layer if their surfaces make a good match to reduce the unpacked gaps within the layer.
 * This property makes it robust. Our heuristic employs both layer packing and wall building approaches. There are some other important methods
 * that our program uses to act boxes efficiently and quickly. One of them is packing a sublayer into any of the available unused space in the
 * last packed layer, which we call a layer-in-layer packing approach. Another new, and the most important, feature of our heuristic is an adaptation
 * of human behavior and intelligence to decide which box to act. Considerable improvement also comes from the data structure we employ. The output of our
 * program contains x, y, z coordinates and x, y, z dimensions of the packed orientation of each packed box.
 */
public class AirForceAlgorithm implements Algorithm {

    private Double bestVolume;
    private Boolean packingBest;
    private double totalItemVolume;

    private LayerService layerService;
    private final ContainerRepository containerRepository;
    private ArticleService articleService;
    private SearchingService searchingService;
    private PackingService packingService;


    /******/

    private double packedVolume;
    private double percentageContainerUsed;
    private double packedy;

    private double layerThickness;
    private double remainpy;
    private double remainpz;
    private double layerinlayer;
    private double prepackedy;
    private double preremainpy;
    private double prelayer;
    private double lilz;


    private long itelayer;
    private long bestIteration;
    private long packedItemCount;
    private long bestVariant;
    private long variant;
    private long ii;


    private boolean packing;
    private boolean hundredPercentPacked;
    private boolean layerDone;
    private boolean evened;


    public AirForceAlgorithm(LayerService layerService,
                             ContainerRepository containerRepository,
                             ArticleService articleService, SearchingService searchingService, PackingService packingService) {
        this.layerService = layerService;
        this.containerRepository = containerRepository;
        this.articleService = articleService;
        this.searchingService = searchingService;
        this.packingService = packingService;
    }

    @Override
    public Result run() {

        // Initialize
        bestVolume = 0.0;
        packingBest = false;
        totalItemVolume = articleService.totalItemVolume();

        for (ContainerOrientation containerOrientation : containerRepository.container().getOrientations()) {
            variant = containerRepository.container().getOrientations().indexOf(containerOrientation);
            articleService.reset();
            List<Layer> layers = layerService.listCandidates(containerOrientation, articleService.articleTypes());
            for (Layer layer : layers) {
                if (containerOrientation.allVolumePacked() || articleService.allPacked()) {
                    break;
                }
                packedVolume = 0;
                packedy = 0;
                packing = true;
                //System.out.println("Assign 2 =" + layers.get(layersIndex).LayerDim);
                layerThickness = layer.getHeight();
                itelayer = layers.indexOf(layer);
                remainpy = containerOrientation.getHeight();
                remainpz = containerOrientation.getLength();
                packedItemCount = 0;
//            dbgLayer(layerThickness, remainpz, layers.get(layersIndex).LayerEval);

                articleService.reset();
                do {
                    //System.out.println("While iterations");
                    layerinlayer = 0;
                    layerDone = false;

                    PackLayer(containerOrientation);

                    packedy = packedy + layerThickness;
                    remainpy = containerOrientation.getHeight() - packedy;

                    if (layerinlayer != 0) {
//                        System.out.println("There is Layer in Layer");
                        prepackedy = packedy;
                        preremainpy = remainpy;
                        remainpy = layerThickness - prelayer;
                        packedy = packedy - layerThickness + prelayer;
                        remainpz = lilz;
                        //System.out.println("Assign 3 =" + layerinlayer);
                        layerThickness = layerinlayer;
                        layerDone = false;
                        System.out.println("LAYER IN LAYER" + (variant + 1) + (itelayer + 1));
                        PackLayer(containerOrientation);

                        packedy = prepackedy;
                        remainpy = preremainpy;
                        remainpz = containerOrientation.getLength();
                    }

                    Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, remainpy, articleService.remainingToPack());
                    if (!foundLayer.isPresent()) {
                        packing = false;
                        System.out.println(remainpy + "FOUND LAYER:" + 0.);
                    } else {
                        layerThickness = foundLayer.get().getHeight();
                        System.out.println(remainpy + "FOUND LAYER:" + layerThickness);
                        if (layerThickness > remainpy) {
                            packing = false;
                        }
                    }
                } while (packing);

                if ((packedVolume > bestVolume)) {
                    bestVolume = packedVolume;
                    bestVariant = variant;
                    bestIteration = itelayer;
//                bestPackedItemCount = packedItemCount;
                }

                if (hundredPercentPacked) {
                    break;
                }

                percentageContainerUsed = bestVolume * 100 / containerOrientation.getVolume();

            }
            if (hundredPercentPacked) {
                break;
            }
        }

//        System.out.println(bestIteration);
//        System.out.println(bestVariant);
        return null;
    }


    private void PackLayer(ContainerOrientation containerOrientation) {
//        System.out.println("Packlayer called");
        double lenx;
        double lenz;
        double lpz;
        ii = 0;

        if (layerThickness == 0) {
            //System.out.println("layerThickness == 0");
            packing = false;
            return;
        }
        TopViewTopology topViewTopology = new TopViewTopologyImpl(new CornerImpl(containerOrientation.getWidth(), 0.));
        //System.out.println("layerthickness is not not");
        //System.out.println("quit" + quit);

        for (; ; ) {
            ii++;
            Corner smallestZ = topViewTopology.findWithSmallestLength();
            //System.out.println("findsmallestz called");
            //System.out.println(quit);
            if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES ***
//                System.out.println("SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES");
                lenx = smallestZ.getWidth();
                lpz = remainpz - smallestZ.getLength();


                SearchResult searchResult = FindBox(lenx, //hmx
                        layerThickness, //hy
                        remainpy, //hmy
                        lpz, //hz
                        lpz);//hmz
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
                    System.out.println("layer done" + (variant + 1) + (itelayer + 1));
                    break;
                }
                if (evened) {
                    //System.out.println("S1 layer evened");
                    continue;
                }

                packingService.pack(containerOrientation, article, new PositionImpl(0D, packedy, smallestZ.getLength()));
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                } else {
                    topViewTopology.addBefore(smallestZ, new CornerImpl(article.getWidth(), smallestZ.getLength() + article.getLength()));
                }
                //System.out.println("S1 Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else if (!smallestZ.hasCornerOnLeft()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-2: NO BOXES ON THE LEFT SIDE ***
//                System.out.println("SITUATION-2: NO BOXES ON THE LEFT SIDE");
                lenx = smallestZ.getWidth();
                lenz = smallestZ.getRight().getLength() - smallestZ.getLength();
                lpz = remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, layerThickness, remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
                    System.out.println("layer done" + (variant + 1) + (itelayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position;
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    position = new PositionImpl(0D, packedy, smallestZ.getLength());
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), packedy, smallestZ.getLength());

                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                packingService.pack(containerOrientation, article, position);
                //System.out.println("S2 Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else if (!smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
//                System.out.println("SITUATION-3: NO BOXES ON THE RIGHT SIDE");
                //*** SITUATION-3: NO BOXES ON THE RIGHT SIDE ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, layerThickness, remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
                    System.out.println("layer done" + (variant + 1) + (itelayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                packingService.pack(containerOrientation, article, new PositionImpl(smallestZ.getLeft().getWidth(), packedy, smallestZ.getLength()));
                if (article.getWidth() == smallestZ.getWidth() - smallestZ.getLeft().getWidth()) {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getWidth());
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                    } else {
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                }
                //System.out.println("S3 Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else if (smallestZ.getLeft().getLength().equals(smallestZ.getRight().getLength())) {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER");
                //*** SITUATION-4: THERE ARE BOXES ON BOTH OF THE SIDES ***

                //*** SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER ***
                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = remainpz - smallestZ.getLength();

                SearchResult searchResult = FindBox(lenx, layerThickness, remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
                    System.out.println("layer done" + (variant + 1) + (itelayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position;
                if (article.getWidth() == smallestZ.getWidth() - smallestZ.getLeft().getWidth()) {
                    position = new PositionImpl(smallestZ.getLeft().getWidth(), packedy, smallestZ.getLength());

                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getRight().getWidth());

                        if (smallestZ.getRight().hasCornerOnRight()) {
                            topViewTopology.remove(smallestZ.getRight());
                            topViewTopology.remove(smallestZ);
                        } else {
                            topViewTopology.removeAndAllOnRight(smallestZ);
                        }
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else if (smallestZ.getLeft().getWidth() < containerOrientation.getWidth() - smallestZ.getWidth()) {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), packedy, smallestZ.getLength());
                    } else {
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), packedy, smallestZ.getLength());
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                } else {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), packedy, smallestZ.getLength());

                    } else {
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), packedy, smallestZ.getLength());
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                packingService.pack(containerOrientation, article, position);
                //System.out.println("S4A Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER");
                //*** SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, layerThickness, remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
                    System.out.println("layer done" + (variant + 1) + (itelayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position = new PositionImpl(smallestZ.getLeft().getWidth(), packedy, smallestZ.getLength());
                if (article.getWidth() == (smallestZ.getWidth() - smallestZ.getLeft().getWidth())) {
                    if ((smallestZ.getLength() + article.getLength()) == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getWidth());
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    if ((smallestZ.getLength() + article.getLength()) == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                    } else if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), packedy, smallestZ.getLength());
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                }
                packingService.pack(containerOrientation, article, position);

                //System.out.println("S4B Volumecheck");
                VolumeCheck(article, containerOrientation);
            }
        }
        //System.out.println("Bye packlayer");
    }

    private ArticleOrientation CheckFound(SearchResult searchResult, Corner smallestZ, TopViewTopology topViewTopology) {
        evened = false;
//        dbgFoundBox(boxi, boxx, boxy, boxz, bboxi, bboxx, bboxy, bboxz);
        if (searchResult.getBestFitInRequired().isPresent()) {
            System.out.println("FITS REQUIRED" + (variant + 1) + (itelayer + 1) + " " + ii);
            return searchResult.getBestFitInRequired().get();
        } else {
            if ((searchResult.getBestFitBiggerThenRequired().isPresent()) &&
                    (layerinlayer != 0 || (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()))) {
                System.out.println("FITS MAX" + (variant + 1) + (itelayer + 1) + " " + ii);
                if (layerinlayer == 0) {
                    prelayer = layerThickness;
                    lilz = smallestZ.getLength();
                }
                ArticleOrientation bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
//                System.out.println("Layer In Layer");
                layerinlayer = layerinlayer + bestFitBiggerThenRequired.getHeight() - layerThickness;

                //System.out.println("Assign 1 =" + bboxy);
                layerThickness = bestFitBiggerThenRequired.getHeight();
                return bestFitBiggerThenRequired;
            } else {
                if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
                    layerDone = true;
                } else {
                    evened = true;

                    if (!smallestZ.hasCornerOnLeft()) {
                        topViewTopology.remove(smallestZ);
                    } else if (!smallestZ.hasCornerOnRight()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getWidth());
                        topViewTopology.remove(smallestZ);
                    } else {
                        if (smallestZ.getLeft().getLength().equals(smallestZ.getRight().getLength())) {
                            smallestZ.getLeft().updateWidth(smallestZ.getRight().getWidth());
                            topViewTopology.remove(smallestZ.getRight());
                            topViewTopology.remove(smallestZ);
                        } else {

                            if (smallestZ.getLeft().getLength() < smallestZ.getRight().getLength()) {
                                smallestZ.getLeft().updateWidth(smallestZ.getWidth());
                            }
                            topViewTopology.remove(smallestZ);
                        }
                    }
                }
                return null;
            }
        }
    }

    private void VolumeCheck(ArticleOrientation article, ContainerOrientation containerOrientation) {

//        itemsToPack.get(cboxi).IsPacked = true;
//        itemsToPack.get(cboxi).PackDimX = cboxx;
//        itemsToPack.get(cboxi).PackDimY = cboxy;
//        itemsToPack.get(cboxi).PackDimZ = cboxz;
////        System.out.println(String.format("Found fits or container layer w:%f,h:%f,l:%f on x:%f,y:%f,z:%f", cboxx, cboxy, cboxz, itemsToPack.get(cboxi).CoordX, itemsToPack.get(cboxi).CoordY, itemsToPack.get(cboxi).CoordZ));
//        //System.out.println("Packed Volume" + packedVolume);
//        Item item = itemsToPack.get(cboxi);
//        dbgPack(item.CoordX, item.CoordY, item.CoordZ, item.PackDimX, item.PackDimY, item.PackDimZ);
        packedVolume = packedVolume + article.getVolume();
        packedItemCount++;

        if (packedVolume == containerOrientation.getVolume() || packedVolume == totalItemVolume) {
            packing = false;
            hundredPercentPacked = true;
        }
    }

    private SearchResult FindBox(double hmx, double hy, double hmy, double hz, double hmz) {
        Gap requiredGapImpl = new GapImpl(hmx, hy, hz);
        Gap maxGapImpl = new GapImpl(hmx, hmy, hmz);
        return searchingService.findBoxTypes(requiredGapImpl, maxGapImpl);
    }
}
