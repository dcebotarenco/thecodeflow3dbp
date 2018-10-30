package com.codeflow.domain.articletype;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    void pack(ArticleOrientation articleOrientation, Position position);

    boolean allPacked();

    Double totalItemVolume();

    void reset();

    Map<ArticleType, Long> articleTypes();

    List<ArticleType> unpackedTypes();

    Map<ArticleType, Long> remainingToPack();

    Map<Position, ArticleOrientation> getPackedTypes();
}
