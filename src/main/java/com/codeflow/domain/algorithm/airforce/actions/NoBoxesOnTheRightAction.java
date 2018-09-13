package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;
import com.codeflow.domain.container.orientation.ContainerOrientation;

public class NoBoxesOnTheRightAction implements Action {
        //*** SITUATION-3: NO BOXES ON THE RIGHT SIDE ***
//
//                lenx = smallestZ.CumX - smallestZ.Pre.CumX;
//                lenz = smallestZ.Pre.CumZ - smallestZ.CumZ;
//                lpz = remainpz - smallestZ.CumZ;
//                FindBox(lenx, layerThickness, remainpy, lenz, lpz);
//                CheckFound();
//
//                if (layerDone) {
//                    //System.out.println("S3 layer done");
//                    break;
//                }
//                if (evened) {
//                    continue;
//                }
//
//                itemsToPack.get(cboxi).CoordY = packedy;
//                itemsToPack.get(cboxi).CoordZ = smallestZ.CumZ;
//                itemsToPack.get(cboxi).CoordX = smallestZ.Pre.CumX;
//
//                if (cboxx == smallestZ.CumX - smallestZ.Pre.CumX) {
//                    if (smallestZ.CumZ + cboxz == smallestZ.Pre.CumZ) {
//                        smallestZ.Pre.CumX = smallestZ.CumX;
//                        smallestZ.Pre.Post = null;
//                    } else {
//                        smallestZ.CumZ = smallestZ.CumZ + cboxz;
//                    }
//                } else {
//                    if (smallestZ.CumZ + cboxz == smallestZ.Pre.CumZ) {
//                        smallestZ.Pre.CumX = smallestZ.Pre.CumX + cboxx;
//                    } else {
//                        smallestZ.Pre.Post = new ScrapPad();
//
//                        smallestZ.Pre.Post.Pre = smallestZ.Pre;
//                        smallestZ.Pre.Post.Post = smallestZ;
//                        smallestZ.Pre = smallestZ.Pre.Post;
//                        smallestZ.Pre.CumX = smallestZ.Pre.Pre.CumX + cboxx;
//                        smallestZ.Pre.CumZ = smallestZ.CumZ + cboxz;
//                    }
//                }
//                //System.out.println("S3 Volumecheck");


    @Override
    public void act(Corner cornerWithSmallestLength, ContainerOrientation containerOrientation, Layer layer) {

    }
}
