package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.algorithm.airforce.searching.SearchResult;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopology;
import com.codeflow.domain.algorithm.airforce.topology.TopViewTopologyImpl;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.algorithm.airforce.topology.corner.CornerImpl;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionImpl;

public class PackLayerAttempt {

    public long packAttempts;
    public boolean layerDone;
    public boolean evened;
    public PackLayerAttemptInput input;
    private PackLayerAttemptResult output;

    public PackLayerAttempt(PackLayerAttemptInput input) {
        this.input = input;
        this.layerDone = false;
    }

    public PackLayerAttemptResult start() {
        this.output = new PackLayerAttemptResult(input);
        PackLayer();
        return output;
    }

    private void PackLayer() {
        double lenx;
        double lenz;
        double lpz;
        packAttempts = 0;

//        if (input.layerThickness == 0) {
//            //System.out.println("layerThickness == 0");
//            input.iterationSession.packing = false;
//            return;
//        }
        TopViewTopology topViewTopology = new TopViewTopologyImpl(new CornerImpl(input.containerOrientation.getWidth(), 0.));
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
                lpz = input.remainpz - smallestZ.getLength();


                SearchResult searchResult = FindBox(lenx, //hmx
                        input.layerThickness, //hy
                        input.remainpy, //hmy
                        lpz, //hz
                        lpz);//hmz
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evened) {
                    //System.out.println("S1 layer evened");
                    continue;
                }
                input.iteration.pack(article, new PositionImpl(0D, input.packedy, smallestZ.getLength()));
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                } else {
                    topViewTopology.addBefore(smallestZ, new CornerImpl(article.getWidth(), smallestZ.getLength() + article.getLength()));
                }
                //System.out.println("S1 Volumecheck");
//                VolumeCheck(article);
            } else if (!smallestZ.hasCornerOnLeft()) {
//                System.out.println(scrapfirst);
                //*** SITUATION-2: NO BOXES ON THE LEFT SIDE ***
//                System.out.println("SITUATION-2: NO BOXES ON THE LEFT SIDE");
                lenx = smallestZ.getWidth();
                lenz = smallestZ.getRight().getLength() - smallestZ.getLength();
                lpz = input.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, input.layerThickness, input.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position;
                if (article.getWidth().equals(smallestZ.getWidth())) {
                    position = new PositionImpl(0D, input.packedy, smallestZ.getLength());
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        topViewTopology.remove(smallestZ);
                    } else {
                        smallestZ.updateLength(smallestZ.getLength() + article.getLength());
                    }
                } else {
                    position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), input.packedy, smallestZ.getLength());

                    if (smallestZ.getLength() + article.getLength() == smallestZ.getRight().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                input.iteration.pack(article, position);
                //System.out.println("S2 Volumecheck");
//                VolumeCheck(article);
            } else if (!smallestZ.hasCornerOnRight()) {
//                System.out.println(scrapfirst);
//                System.out.println("SITUATION-3: NO BOXES ON THE RIGHT SIDE");
                //*** SITUATION-3: NO BOXES ON THE RIGHT SIDE ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = input.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, input.layerThickness, input.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }
                input.iteration.pack(article, new PositionImpl(smallestZ.getLeft().getWidth(), input.packedy, smallestZ.getLength()));
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
//                VolumeCheck(article);
            } else if (smallestZ.getLeft().getLength().equals(smallestZ.getRight().getLength())) {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER");
                //*** SITUATION-4: THERE ARE BOXES ON BOTH OF THE SIDES ***

                //*** SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER ***
                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = input.remainpz - smallestZ.getLength();

                SearchResult searchResult = FindBox(lenx, input.layerThickness, input.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position;
                if (article.getWidth() == smallestZ.getWidth() - smallestZ.getLeft().getWidth()) {
                    position = new PositionImpl(smallestZ.getLeft().getWidth(), input.packedy, smallestZ.getLength());

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
                } else if (smallestZ.getLeft().getWidth() < input.containerOrientation.getWidth() - smallestZ.getWidth()) {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), input.packedy, smallestZ.getLength());
                    } else {
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), input.packedy, smallestZ.getLength());
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                } else {
                    if (smallestZ.getLength() + article.getLength() == smallestZ.getLeft().getLength()) {
                        smallestZ.getLeft().updateWidth(smallestZ.getLeft().getWidth() + article.getWidth());
                        position = new PositionImpl(smallestZ.getLeft().getWidth(), input.packedy, smallestZ.getLength());

                    } else {
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), input.packedy, smallestZ.getLength());
                        topViewTopology.addAfter(smallestZ, new CornerImpl(smallestZ.getWidth(), smallestZ.getLength() + article.getLength()));
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    }
                }
                input.iteration.pack(article, position);
                //System.out.println("S4A Volumecheck");
//                VolumeCheck(article);
            } else {
//                System.out.println(scrapfirst);
//                System.out.println("SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER");
                //*** SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER ***

                lenx = smallestZ.getWidth() - smallestZ.getLeft().getWidth();
                lenz = smallestZ.getLeft().getLength() - smallestZ.getLength();
                lpz = input.remainpz - smallestZ.getLength();
                SearchResult searchResult = FindBox(lenx, input.layerThickness, input.remainpy, lenz, lpz);
                ArticleOrientation article = CheckFound(searchResult, smallestZ, topViewTopology);

                if (layerDone) {
//                    System.out.println("layer done" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1));
                    break;
                }
                if (evened) {
                    continue;
                }

                Position position = new PositionImpl(smallestZ.getLeft().getWidth(), input.packedy, smallestZ.getLength());
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
                        position = new PositionImpl(smallestZ.getWidth() - article.getWidth(), input.packedy, smallestZ.getLength());
                        smallestZ.updateWidth(smallestZ.getWidth() - article.getWidth());
                    } else {
                        topViewTopology.addBefore(smallestZ, new CornerImpl(smallestZ.getLeft().getWidth() + article.getWidth(), smallestZ.getLength() + article.getLength()));
                    }
                }
                input.iteration.pack(article, position);

                //System.out.println("S4B Volumecheck");
//                VolumeCheck(article);
            }
        }
        //System.out.println("Bye packlayer");
    }

    private ArticleOrientation CheckFound(SearchResult searchResult, Corner smallestZ, TopViewTopology topViewTopology) {
        evened = false;
//        dbgFoundBox(boxi, boxx, boxy, boxz, bboxi, bboxx, bboxy, bboxz);
        if (searchResult.getBestFitInRequired().isPresent()) {
//            System.out.println("FITS REQUIRED" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
            return searchResult.getBestFitInRequired().get();
        } else {
            if ((searchResult.getBestFitBiggerThenRequired().isPresent()) &&
                    (output.layerinlayer != 0 || (!smallestZ.hasCornerOnLeft() && !smallestZ.hasCornerOnRight()))) {
//                System.out.println("FITS MAX" + (iterationSession.currentIndexOfContainerOrientation + 1) + (iterationSession.currentIndexOfLayer + 1) + " " + packLayer.packAttempts);
                if (output.layerinlayer == 0) {
                    output.prelayer = input.layerThickness;
                    output.lilz = smallestZ.getLength();
                }
                ArticleOrientation bestFitBiggerThenRequired = searchResult.getBestFitBiggerThenRequired().get();
//                System.out.println("Layer In Layer");
                output.layerinlayer = output.layerinlayer + bestFitBiggerThenRequired.getHeight() - input.layerThickness;

                //System.out.println("Assign 1 =" + bboxy);
                output.layerThickness = bestFitBiggerThenRequired.getHeight();
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

    private SearchResult FindBox(double hmx, double hy, double hmy, double hz, double hmz) {
        Gap requiredGapImpl = new GapImpl(hmx, hy, hz);
        Gap maxGapImpl = new GapImpl(hmx, hmy, hmz);
        return input.iteration.findBoxTypes(input.iteration.getIterationStock().articleTypes(), requiredGapImpl, maxGapImpl);
    }
}
