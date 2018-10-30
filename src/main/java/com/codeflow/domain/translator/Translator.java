package com.codeflow.domain.translator;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;

public interface Translator {
    Position translate(Position position);

    ArticleOrientation translate(ArticleOrientation orientation);
}

