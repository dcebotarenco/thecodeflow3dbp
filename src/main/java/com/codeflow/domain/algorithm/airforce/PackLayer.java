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

    public PackLayer(IterationSession iterationSession,
                     ContainerOrientation containerOrientation,
                     LayerService layerService) {
        this.layerinlayer = 0;
        this.layerDone = false;
        this.iterationSession = iterationSession;
        this.layerService = layerService;
        this.containerOrientation = containerOrientation;
    }

    public void run() {
        PackLayer();

        iterationSession.packedy = iterationSession.packedy + iterationSession.layerThickness;
        iterationSession.remainpy = containerOrientation.getHeight() - iterationSession.packedy;

        if (this.layerinlayer != 0) {
//                        System.out.println("There is Layer in Layer");
            this.prepackedy = iterationSession.packedy;
            this.preremainpy = iterationSession.remainpy;
            iterationSession.remainpy = iterationSession.layerThickness - this.prelayer;
            iterationSession.packedy = iterationSession.packedy - iterationSession.layerThickness + this.prelayer;
            iterationSession.remainpz = this.lilz;
            //System.out.println("Assign 3 =" + layerinlayer);
            iterationSession.layerThickness = this.layerinlayer;
            this.layerDone = false;
//            System.out.println("LAYER IN LAYER" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
            PackLayer();

            iterationSession.packedy = this.prepackedy;
            iterationSession.remainpy = this.preremainpy;
            iterationSession.remainpz = containerOrientation.getLength();
        }

        Optional<Layer> foundLayer = layerService.findLayer(containerOrientation, iterationSession.remainpy, iterationSession.getRemainingToPack());
        if (!foundLayer.isPresent()) {
            iterationSession.packing = false;
            System.out.println(iterationSession.remainpy + "FOUND LAYER:" + 0.);
        } else {
            iterationSession.layerThickness = foundLayer.get().getHeight();
            System.out.println(iterationSession.remainpy + "FOUND LAYER:" + iterationSession.layerThickness);
            if (iterationSession.layerThickness > iterationSession.remainpy) {
                iterationSession.packing = false;
            }
        }
    }

    private void PackLayer() {
//        System.out.println("Packlayer called");
        double lenx;
        double lenz;
        double lpz;
        packAttempts = 0;

        if (iterationSession.layerThickness == 0) {
            //System.out.println("layerThickness == 0");
            iterationSession.packing = false;
            return;
        }
        TopViewTopology topViewTopology = new TopViewTopologyImpl(new CornerImpl(containerOrientation.getWidth(), 0.));
        //System.out.println("layerthickness is not not");
        //System.out.println("quit" + quit);

        for (; ; ) {
            packAttempts++;
            Corner smallestZ = topViewTopology.findWithSmallestLength();
            //System.out.println("findsmallestz called");
            //System.out.println(quit);
            if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES ***
//                System.out.println("SITUATION-1: NO BOXES ON THE RIGHT AND LEFT SIDES");
                lenx = smallestZ.getWidth();
                lpz = iterationSession.remainpz - smallestZ.getLength();


                SearchResult searchResult = FindBox(lenx, //hmx
                        iterationSession.layerThickness, //hy
                        iterationSession.remainpy, //hmy
                        lpz, //hz
                        lpz);//hmz
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evenedPerSearch) {
                    //System.out.println("S1 layer evenedPerSearch");
                    continue;
                }
                iterationSession.pack(article, new PositionImpl(0D, iterationSession.packedy, smallestZ.getLength()));
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
                lpz = iterationSession.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationSession.layerThickness, iterationSession.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evenedPerSearch) {
                    continue;
                }

                Position position;
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    position = new PositionImpl(0D, iterationSession.packedy, smallestZ.getLength());
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationSession.packedy, smallestZ.getLength());

                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                iterationSession.pack(article, position);
                //System.out.println("S2 Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else if (!smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
//                System.out.println("SITUATION-3: NO BOXES ON THE RIGHT SIDE");
                //*** SITUATION-3: NO BOXES ON THE RIGHT SIDE ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = iterationSession.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationSession.layerThickness, iterationSession.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evenedPerSearch) {
                    continue;
                }
                iterationSession.pack(article, new PositionImpl(smallestZ.getLeft().getWidth(), iterationSession.packedy, smallestZ.getLength()));
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
                lpz = iterationSession.remainpz - smallestZ.getLength();

                SearchResult searchResult = FindBox(lenx, iterationSession.layerThickness, iterationSession.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evenedPerSearch) {
                    continue;
                }

                Position position;
                if (article.getWidth() == smallestZ.getWidth() - smallestZ.getLeft().getWidth()) {
                    position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationSession.packedy, smallestZ.getLength());

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
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationSession.packedy, smallestZ.getLength());
                    } else {
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationSession.packedy, smallestZ.getLength());
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                } else {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationSession.packedy, smallestZ.getLength());

                    } else {
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationSession.packedy, smallestZ.getLength());
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                iterationSession.pack(article, position);
                //System.out.println("S4A Volumecheck");
                VolumeCheck(article, containerOrientation);
            } else {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER");
                //*** SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = iterationSession.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, iterationSession.layerThickness, iterationSession.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evenedPerSearch) {
                    continue;
                }

                Position position = new PositionImpl(smallestZ.getLeft().getWidth(), iterationSession.packedy, smallestZ.getLength());
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
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), iterationSession.packedy, smallestZ.getLength());
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                }
                iterationSession.pack(article, position);

                //System.out.println("S4B Volumecheck");
                VolumeCheck(article, containerOrientation);
            }
        }
        //System.out.println("Bye packlayer");
    }

    private ArticleOrientation CheckFound(SearchResult searchResult, Corner smallestZ, TopViewTopology topViewTopology) {
        evenedPerSearch = false;
//        dbgFoundBox(boxi, boxx, boxy, boxz, bboxi, bboxx, bboxy, bboxz);
        if (searchResult.getBestFitInRequired().isPresent()) {
//            System.out.println("FITS REQUIRED" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
            return searchResult.getBestFitInRequired().get();
        } else {
            if ((searchResult.getBestFitBiggerThenRequired().isPresent()) &&
                    (layerinlayer != 0 || (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()))) {
//                System.out.println("FITS MAX" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
                if (layerinlayer == 0) {
                    prelayer = iterationSession.layerThickness;
                    lilz = smallestZ.getLength();
                }
                ArticleOrientation bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
//                System.out.println("Layer In Layer");
                layerinlayer = layerinlayer + bestFitBiggerThenRequired.getHeight() - iterationSession.layerThickness;

                //System.out.println("Assign 1 =" + bboxy);
                iterationSession.layerThickness = bestFitBiggerThenRequired.getHeight();
                return bestFitBiggerThenRequired;
            } else {
                if (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()) {
                    layerDone = true;
                } else {
                    evenedPerSearch = true;

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

    private void VolumeCheck(ArticleOrientation foundArticle, ContainerOrientation containerOrientation) {

//        itemsToPack.get(cboxi).IsPacked = true;
//        itemsToPack.get(cboxi).PackDimX = cboxx;
//        itemsToPack.get(cboxi).PackDimY = cboxy;
//        itemsToPack.get(cboxi).PackDimZ = cboxz;
////        System.out.println(String.format("Found fits or container layer w:%f,h:%f,l:%f on x:%f,y:%f,z:%f", cboxx, cboxy, cboxz, itemsToPack.get(cboxi).CoordX, itemsToPack.get(cboxi).CoordY, itemsToPack.get(cboxi).CoordZ));
//        //System.out.println("Packed Volume" + packedVolume);
//        Item item = itemsToPack.get(cboxi);
//        dbgPack(item.CoordX, item.CoordY, item.CoordZ, item.PackDimX, item.PackDimY, item.PackDimZ);
        iterationSession.packedVolume = iterationSession.packedVolume + foundArticle.getVolume();
        iterationSession.packedItemCount++;

        if (iterationSession.packedVolume == containerOrientation.getVolume() || iterationSession.packedVolume == iterationSession.totalVolume) {
            iterationSession.packing = false;
            iterationSession.hundredPercentPacked = true;
        }
    }

    private SearchResult FindBox(double hmx, double hy, double hmy, double hz, double hmz) {
        Gap requiredGapImpl = new GapImpl(hmx, hy, hz);
        Gap maxGapImpl = new GapImpl(hmx, hmy, hmz);
        return iterationSession.findBoxTypes(iterationSession.unpackedTypes(), requiredGapImpl, maxGapImpl);
    }

}
