package com.codeflow.domain.articletype;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {

    private Map<Position, ArticleOrientation> packedTypes;

    public ArticleServiceImpl() {
        this.packedTypes = new LinkedHashMap<>();
    }

}
