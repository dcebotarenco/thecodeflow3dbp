package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.algorithm.airforce.topology.corner.Corner;

public class ThereAreBoxesOnBothSidesAndNotEqualAction implements Action {
//        System.out.println("SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER");
//        //*** SUBSITUATION-4B: SIDES ARE NOT EQUAL TO EACH OTHER ***
//
//        lenx = smallestZ.CumX - smallestZ.Pre.CumX;
//        lenz = smallestZ.Pre.CumZ - smallestZ.CumZ;
//        lpz = remainpz - smallestZ.CumZ;
//        FindBox(lenx, layerThickness, remainpy, lenz, lpz);
//        CheckFound();
//
//        if (layerDone) {
//            //System.out.println("S4B layer done");
//            break;
//        }
//        if (evened) {
//            continue;
//        }
//
//        itemsToPack.get(cboxi).CoordY = packedy;
//        itemsToPack.get(cboxi).CoordZ = smallestZ.CumZ;
//        itemsToPack.get(cboxi).CoordX = smallestZ.Pre.CumX;
//
//        if (cboxx == (smallestZ.CumX - smallestZ.Pre.CumX)) {
//            if ((smallestZ.CumZ + cboxz) == smallestZ.Pre.CumZ) {
//                smallestZ.Pre.CumX = smallestZ.CumX;
//                smallestZ.Pre.Post = smallestZ.Post;
//                smallestZ.Post.Pre = smallestZ.Pre;
//            } else {
//                smallestZ.CumZ = smallestZ.CumZ + cboxz;
//            }
//        } else {
//            if ((smallestZ.CumZ + cboxz) == smallestZ.Pre.CumZ) {
//                smallestZ.Pre.CumX = smallestZ.Pre.CumX + cboxx;
//            } else if (smallestZ.CumZ + cboxz == smallestZ.Post.CumZ) {
//                itemsToPack.get(cboxi).CoordX = smallestZ.CumX - cboxx;
//                smallestZ.CumX = smallestZ.CumX - cboxx;
//            } else {
//                smallestZ.Pre.Post = new ScrapPad();
//
//                smallestZ.Pre.Post.Pre = smallestZ.Pre;
//                smallestZ.Pre.Post.Post = smallestZ;
//                smallestZ.Pre = smallestZ.Pre.Post;
//                smallestZ.Pre.CumX = smallestZ.Pre.Pre.CumX + cboxx;
//                smallestZ.Pre.CumZ = smallestZ.CumZ + cboxz;
//            }
//        }

    @Override
    public void act(Corner cornerWithSmallestLength, Double remainingHeight, Double remainingLength, Layer layer) {

    }
}
