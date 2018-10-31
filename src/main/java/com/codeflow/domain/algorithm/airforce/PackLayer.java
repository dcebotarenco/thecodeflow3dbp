package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.layer.LayerService;
import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyImpl;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerImpl;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionImpl;

import java.util.Optional;

public class PackLayer {

    public double layerinlayer;
    public boolean layerDone;
    public double prepackedy;
    public double preremainpy;
    public double prelayer;
    public double lilz;
    public long packAttempts;
    public boolean evenedPerSearch;
    private IterationSession iterationSession;
    private LayerService layerService;
    private ContainerOrientation containerOrientation;
    private Run run;
    private Iteration iterationPerLayer;

    public PackLayer(IterationSession iterationSession,
                     LayerService layerService,
                     ContainerOrientation containerOrientation,
                     Run run,
                     Iteration iterationPerLayer) {
        this.layerinlayer = 0;
        this.layerDone = false;
        this.iterationSession = iterationSession;
        this.layerService = layerService;
        this.containerOrientation = containerOrientation;
        this.run = run;
        this.iterationPerLayer = iterationPerLayer;
    }

    public void run() {
        PackLayer(containerOrientation, run, iterationPerLayer, this);

        iterationPerLayer.packedy = iterationPerLayer.packedy + iterationPerLayer.layerThickness;
        iterationPerLayer.remainpy = containerOrientation.getHeight() - iterationPerLayer.packedy;

        if (this.layerinlayer != 0) {
//                        System.out.println("There is Layer in Layer");
            this.prepackedy = iterationPerLayer.packedy;
            this.preremainpy = iterationPerLayer.remainpy;
            iterationPerLayer.remainpy = iterationPerLayer.layerThickness - this.prelayer;
            iterationPerLayer.packedy = iterationPerLayer.packedy - iterationPerLayer.layerThickness + this.prelayer;
            iterationPerLayer.remainpz = this.lilz;
            //System.out.println("Assign 3 =" + layerinlayer);
            iterationPerLayer.layerThickness = this.layerinlayer;
            this.layerDone = false;
            System.out.println("LAYER IN LAYER" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
            PackLayer(containerOrientation, run, iterationPerLayer, this);

            iterationPerLayer.packedy = this.prepackedy;
            iterationPerLayer.remainpy = this.preremainpy;
            iterationPerLayer.remainpz = containerOrientation.getLength();
        }

        Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, iterationPerLayer.remainpy, iterationSession.getRemainingToPack());
        if (!foundLayer.isPresent()) {
            iterationPerLayer.packing = false;
            System.out.println(iterationPerLayer.remainpy + "FOUND LAYER:" + 0.);
        } else {
            iterationPerLayer.layerThickness = foundLayer.get().getHeight();
            System.out.println(iterationPerLayer.remainpy + "FOUND LAYER:" + iterationPerLayer.layerThickness);
            if (iterationPerLayer.layerThickness > iterationPerLayer.remainpy) {
                iterationPerLayer.packing = false;
            }
        }
    }

    private void PackLayer(ContainerOrientation containerOrientation, Run run, Iteration iterationPerLayer, PackLayer packLayer) {
//        System.out.println("Packlayer called");
        double lenx;
        double lenz;
        double lpz;
        packLayer.packAttempts = 0;

        if (iterationPerLayer.layerThickness == 0) {
            //System.out.println("layerThickness == 0");
            iterationPerLayer.packing = false;
            return;
        }
        TopViewTopology topViewTopology = new TopViewTopologyImpl(new CornerImpl(containerOrientation.getWidth(), 0.));
        //System.out.println("layerthickness is not not");
        //System.out.println("quit" + quit);

        for (; ; ) {
            packLayer.packAttempts++;
            Corner smallestZ = topViewTopology.findWithSmallestLength();
            //System.out.println("findsmallestz called");
            //System.out.println(quit);
            if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES ***
//                System.out.println("SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES");
                lenx = smallestZ.getWidth();
                lpz = iterationPerLayer.remainpz - smallestZ.getLength();


                SearchResult searchResult = FindBox(lenx, //hmx
                        iterationPerLayer.layerThickness, //hy
                        iterationPerLayer.remainpy, //hmy
                        lpz, //hz
                        lpz);//hmz
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology, iterationPerLayer, packLayer);

                if (packLayer.layerDone) {
                    System.out.println("layer done" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
                    break;
                }
                if (packLayer.evenedPerSearch) {
                    //System.out.println("S1 layer evenedPerSearch");
                    continue;
                }
                iterationSession.pack(article, new PositionImpl(0D, iterationPerLayer.packedy, smallestZ.getLength()));
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                } else {
                    topViewTopology.addBefore(smallestZ, new CornerImpl(article.getWidth(), smallestZ.getLength() + article.getLength()));
                }
                //System.out.println("S1 Volumecheck");
                VolumeCheck(article, containerOrientation, iterationPerLayer, run);
            } else if (!smallestZ.hasCornerOnLeft()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-2: NO BOXES ON THE LEFT SIDE ***
//                System.out.println("SITUATION-2: NO BOXES ON THE LEFT SIDE");
                lenx = smallestZ.getWidth();
                lenz = smallestZ.getRight().getLength() - smallestZ.getLength();
                lpz = iterationPerLayer.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationPerLayer.layerThickness, iterationPerLayer.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology, iterationPerLayer, packLayer);

                if (packLayer.layerDone) {
                    System.out.println("layer done" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
                    break;
                }
                if (packLayer.evenedPerSearch) {
                    continue;
                }

                Position position;
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    position = new PositionImpl(0D, iterationPerLayer.packedy, smallestZ.getLength());
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationPerLayer.packedy, smallestZ.getLength());

                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                iterationSession.pack(article, position);
                //System.out.println("S2 Volumecheck");
                VolumeCheck(article, containerOrientation, iterationPerLayer, run);
            } else if (!smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
//                System.out.println("SITUATION-3: NO BOXES ON THE RIGHT SIDE");
                //*** SITUATION-3: NO BOXES ON THE RIGHT SIDE ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = iterationPerLayer.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationPerLayer.layerThickness, iterationPerLayer.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology, iterationPerLayer, packLayer);

                if (packLayer.layerDone) {
                    System.out.println("layer done" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
                    break;
                }
                if (packLayer.evenedPerSearch) {
                    continue;
                }
                iterationSession.pack(article, new PositionImpl(smallestZ.getLeft().getWidth(), iterationPerLayer.packedy, smallestZ.getLength()));
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
                VolumeCheck(article, containerOrientation, iterationPerLayer, run);
            } else if (smallestZ.getLeft().getLength().equals(smallestZ.getRight().getLength())) {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER");
                //*** SITUATION-4: THERE ARE BOXES ON BOTH OF THE SIDES ***

                //*** SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER ***
                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = iterationPerLayer.remainpz - smallestZ.getLength();

                SearchResult searchResult = FindBox(lenx, iterationPerLayer.layerThickness, iterationPerLayer.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology, iterationPerLayer, packLayer);

                if (packLayer.layerDone) {
                    System.out.println("layer done" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
                    break;
                }
                if (packLayer.evenedPerSearch) {
                    continue;
                }

                Position position;
                if (article.getWidth() == smallestZ.getWidth() - smallestZ.getLeft().getWidth()) {
                    position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationPerLayer.packedy, smallestZ.getLength());

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
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationPerLayer.packedy, smallestZ.getLength());
                    } else {
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationPerLayer.packedy, smallestZ.getLength());
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                } else {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationPerLayer.packedy, smallestZ.getLength());

                    } else {
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationPerLayer.packedy, smallestZ.getLength());
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                iterationSession.pack(article, position);
                //System.out.println("S4A Volumecheck");
                VolumeCheck(article, containerOrientation, iterationPerLayer, run);
            } else {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER");
                //*** SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = iterationPerLayer.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationPerLayer.layerThickness, iterationPerLayer.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology, iterationPerLayer, packLayer);

                if (packLayer.layerDone) {
                    System.out.println("layer done" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1));
                    break;
                }
                if (packLayer.evenedPerSearch) {
                    continue;
                }

                Position position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationPerLayer.packedy, smallestZ.getLength());
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
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationPerLayer.packedy, smallestZ.getLength());
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                }
                iterationSession.pack(article, position);

                //System.out.println("S4B Volumecheck");
                VolumeCheck(article, containerOrientation, iterationPerLayer, run);
            }
        }
        //System.out.println("Bye packlayer");
    }

    private ArticleOrientation CheckFound(SearchResult searchResult, Corner smallestZ, TopViewTopology topViewTopology, Iteration iterationPerLayer, PackLayer packLayer) {
        packLayer.evenedPerSearch = false;
//        dbgFoundBox(boxi, boxx, boxy, boxz, bboxi, bboxx, bboxy, bboxz);
        if (searchResult.getBestFitInRequired().isPresent()) {
            System.out.println("FITS REQUIRED" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
            return searchResult.getBestFitInRequired().get();
        } else {
            if ((searchResult.getBestFitBiggerThenRequired().isPresent()) &&
                    (packLayer.layerinlayer != 0 || (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()))) {
                System.out.println("FITS MAX" + (iterationPerLayer.currentIndexOfContainerOrientation + 1) + (iterationPerLayer.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
                if (packLayer.layerinlayer == 0) {
                    packLayer.prelayer = iterationPerLayer.layerThickness;
                    packLayer.lilz = smallestZ.getLength();
                }
                ArticleOrientation bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
//                System.out.println("Layer In Layer");
                packLayer.layerinlayer = packLayer.layerinlayer + bestFitBiggerThenRequired.getHeight() - iterationPerLayer.layerThickness;

                //System.out.println("Assign 1 =" + bboxy);
                iterationPerLayer.layerThickness = bestFitBiggerThenRequired.getHeight();
                return bestFitBiggerThenRequired;
            } else {
                if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
                    packLayer.layerDone = true;
                } else {
                    packLayer.evenedPerSearch = true;

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

    private void VolumeCheck(ArticleOrientation foundArticle, ContainerOrientation containerOrientation, Iteration iterationPerLayer, Run run) {

//        itemsToPack.get(cboxi).IsPacked = true;
//        itemsToPack.get(cboxi).PackDimX = cboxx;
//        itemsToPack.get(cboxi).PackDimY = cboxy;
//        itemsToPack.get(cboxi).PackDimZ = cboxz;
////        System.out.println(String.format("Found fits or container layer w:%f,h:%f,l:%f on x:%f,y:%f,z:%f", cboxx, cboxy, cboxz, itemsToPack.get(cboxi).CoordX, itemsToPack.get(cboxi).CoordY, itemsToPack.get(cboxi).CoordZ));
//        //System.out.println("Packed Volume" + packedVolume);
//        Item item = itemsToPack.get(cboxi);
//        dbgPack(item.CoordX, item.CoordY, item.CoordZ, item.PackDimX, item.PackDimY, item.PackDimZ);
        iterationPerLayer.packedVolume = iterationPerLayer.packedVolume + foundArticle.getVolume();
        iterationPerLayer.packedItemCount++;

        if (iterationPerLayer.packedVolume == containerOrientation.getVolume() || iterationPerLayer.packedVolume == run.totalItemVolume) {
            iterationPerLayer.packing = false;
            run.hundredPercentPackedPerSearch = true;
        }
    }

    private SearchResult FindBox(double hmx, double hy, double hmy, double hz, double hmz) {
        Gap requiredGapImpl = new GapImpl(hmx, hy, hz);
        Gap maxGapImpl = new GapImpl(hmx, hmy, hmz);
        return iterationSession.findBoxTypes(iterationSession.unpackedTypes(), requiredGapImpl, maxGapImpl);
    }

}
