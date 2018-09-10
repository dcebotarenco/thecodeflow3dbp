package com.codeflow.domain.algorithm.airforce.packing;

public class ThereAreBoxesOnBothSidesAndEqualAction implements Runnable {
    @Override
    public void run() {
//        System.out.println("SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER");
//        //*** SITUATION-4: THERE ARE BOXES ON BOTH OF THE SIDES ***
//
//        //*** SUBSITUATION-4A: SIDES ARE EQUAL TO EACH OTHER ***
//        lenx = smallestZ.CumX - smallestZ.Pre.CumX;
//        lenz = smallestZ.Pre.CumZ - smallestZ.CumZ;
//        lpz = remainpz - smallestZ.CumZ;
//
//        FindBox(lenx, layerThickness, remainpy, lenz, lpz);
//        CheckFound();
//
//        if (layerDone) {
//            //System.out.println("S4A layer done");
//            break;
//        }
//        if (evened) {
//            continue;
//        }
//
//        itemsToPack.get(cboxi).CoordY = packedy;
//        itemsToPack.get(cboxi).CoordZ = smallestZ.CumZ;
//
//        if (cboxx == smallestZ.CumX - smallestZ.Pre.CumX) {
//            itemsToPack.get(cboxi).CoordX = smallestZ.Pre.CumX;
//
//            if (smallestZ.CumZ + cboxz == smallestZ.Post.CumZ) {
//                smallestZ.Pre.CumX = smallestZ.Post.CumX;
//
//                if (smallestZ.Post.Post != null) {
//                    smallestZ.Pre.Post = smallestZ.Post.Post;
//                    smallestZ.Post.Post.Pre = smallestZ.Pre;
//                } else {
//                    smallestZ.Pre.Post = null;
//                }
//            } else {
//                smallestZ.CumZ = smallestZ.CumZ + cboxz;
//            }
//        } else if (smallestZ.Pre.CumX < px - smallestZ.CumX) {
//            if (smallestZ.CumZ + cboxz == smallestZ.Pre.CumZ) {
//                smallestZ.CumX = smallestZ.CumX - cboxx;
//                itemsToPack.get(cboxi).CoordX = smallestZ.CumX - cboxx;
//            } else {
//                itemsToPack.get(cboxi).CoordX = smallestZ.Pre.CumX;
//                smallestZ.Pre.Post = new ScrapPad();
//
//                smallestZ.Pre.Post.Pre = smallestZ.Pre;
//                smallestZ.Pre.Post.Post = smallestZ;
//                smallestZ.Pre = smallestZ.Pre.Post;
//                smallestZ.Pre.CumX = smallestZ.Pre.Pre.CumX + cboxx;
//                smallestZ.Pre.CumZ = smallestZ.CumZ + cboxz;
//            }
//        } else {
//            if (smallestZ.CumZ + cboxz == smallestZ.Pre.CumZ) {
//                smallestZ.Pre.CumX = smallestZ.Pre.CumX + cboxx;
//                itemsToPack.get(cboxi).CoordX = smallestZ.Pre.CumX;
//            } else {
//                itemsToPack.get(cboxi).CoordX = smallestZ.CumX - cboxx;
//                smallestZ.Post.Pre = new ScrapPad();
//
//                smallestZ.Post.Pre.Post = smallestZ.Post;
//                smallestZ.Post.Pre.Pre = smallestZ;
//                smallestZ.Post = smallestZ.Post.Pre;
//                smallestZ.Post.CumX = smallestZ.CumX;
//                smallestZ.Post.CumZ = smallestZ.CumZ + cboxz;
//                smallestZ.CumX = smallestZ.CumX - cboxx;
//            }
//        }
        //System.out.println("S4A Volumecheck");
    }
}
