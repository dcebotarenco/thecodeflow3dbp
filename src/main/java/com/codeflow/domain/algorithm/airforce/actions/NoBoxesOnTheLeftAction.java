package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.container.orientation.ContainerOrientation;

public class NoBoxesOnTheLeftAction implements Action {

    //*** SITUATION-2: NO BOXES ON THE LEFT SIDE ***
//                System.out.println("SITUATION-2: NO BOXES ON THE LEFT SIDE");
//                lenx = smallestZ.CumX;
//                lenz = smallestZ.Post.CumZ - smallestZ.CumZ;
//                lpz = remainpz - smallestZ.CumZ;
//                FindBox(lenx, layerThickness, remainpy, lenz, lpz);
//                CheckFound();
//
//                if (layerDone) {
//                    //System.out.println("S2 layer done");
//                    break;
//                }
//                if (evened) {
//                    continue;
//                }
//
//                itemsToPack.get(cboxi).CoordY = packedy;
//                itemsToPack.get(cboxi).CoordZ = smallestZ.CumZ;
//                if (cboxx == smallestZ.CumX) {
//                    itemsToPack.get(cboxi).CoordX = 0;
//
//                    if (smallestZ.CumZ + cboxz == smallestZ.Post.CumZ) {
//                        smallestZ.CumZ = smallestZ.Post.CumZ;
//                        smallestZ.CumX = smallestZ.Post.CumX;
//                        trash = smallestZ.Post;
//                        smallestZ.Post = smallestZ.Post.Post;
//
//                        if (smallestZ.Post != null) {
//                            smallestZ.Post.Pre = smallestZ;
//                        }
//                    } else {
//                        smallestZ.CumZ = smallestZ.CumZ + cboxz;
//                    }
//                } else {
//                    itemsToPack.get(cboxi).CoordX = smallestZ.CumX - cboxx;
//
//                    if (smallestZ.CumZ + cboxz == smallestZ.Post.CumZ) {
//                        smallestZ.CumX = smallestZ.CumX - cboxx;
//                    } else {
//                        smallestZ.Post.Pre = new ScrapPad();
//
//                        smallestZ.Post.Pre.Post = smallestZ.Post;
//                        smallestZ.Post.Pre.Pre = smallestZ;
//                        smallestZ.Post = smallestZ.Post.Pre;
//                        smallestZ.Post.CumX = smallestZ.CumX;
//                        smallestZ.CumX = smallestZ.CumX - cboxx;
//                        smallestZ.Post.CumZ = smallestZ.CumZ + cboxz;
//                    }
//                }
//                //System.out.println("S2 Volumecheck");


    @Override
    public void act(Corner cornerWithSmallestLength, ContainerOrientation containerOrientation, Layer layer) {

    }
}
