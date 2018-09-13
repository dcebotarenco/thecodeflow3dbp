package com.codeflow.domain.algorithm.airforce.packing;

import com.codeflow.domain.algorithm.airforce.searching.Position;
import com.codeflow.domain.article.Article;
import com.codeflow.domain.article.ArticleRepository;
import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.orientation.Orientation;

class PackingServiceImpl implements PackingService {

    private BoxTypeRepository boxTypeRepository;
    private ArticleRepository articleRepository;

    PackingServiceImpl(BoxTypeRepository boxTypeRepository, ArticleRepository articleRepository) {
        this.boxTypeRepository = boxTypeRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void pack(Orientation orientation, Position position) {
        BoxType boxType = boxTypeRepository.findByOrientation(orientation);
        Article articleImpl = articleRepository.findByBoxType(boxType);
        articleRepository.removeReceived(articleImpl);
        articleRepository.savePacked(articleImpl, position);
//        itemsToPack.get(cboxi).IsPacked = true;
//        itemsToPack.get(cboxi).PackDimX = cboxx;
//        itemsToPack.get(cboxi).PackDimY = cboxy;
//        itemsToPack.get(cboxi).PackDimZ = cboxz;
////        System.out.println(String.format("Found fits or container layer w:%f,h:%f,l:%f on x:%f,y:%f,z:%f", cboxx, cboxy, cboxz, itemsToPack.get(cboxi).CoordX, itemsToPack.get(cboxi).CoordY, itemsToPack.get(cboxi).CoordZ));
//        //System.out.println("Packed Volume" + packedVolume);
//        packedVolume = packedVolume + itemsToPack.get(cboxi).Volume;
//        packedItemCount++;
    }
}
