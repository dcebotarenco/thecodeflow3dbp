package com.codeflow.domain.translator;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.articletype.orientation.ArticleOrientationImpl;
import com.codeflow.domain.position.Position;
import com.codeflow.domain.position.PositionImpl;

public class WHLTranslator implements Translator {

    @Override
    public Position translate(Position position) {
        return new PositionImpl(position.getX(), position.getY(), position.getZ());
    }

    @Override
    public ArticleOrientation translate(ArticleOrientation orientation) {
        return new ArticleOrientationImpl(orientation.getWidth(), orientation.getHeight(), orientation.getLength(), orientation.getBoxType());
    }
}
