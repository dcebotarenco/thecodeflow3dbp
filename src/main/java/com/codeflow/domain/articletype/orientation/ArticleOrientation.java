package com.codeflow.domain.articletype.orientation;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.orientation.Orientation;

public interface ArticleOrientation extends Orientation {

    ArticleType getBoxType();
}
