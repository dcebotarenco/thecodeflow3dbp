package com.codeflow.domain.iteration;

import com.codeflow.domain.algorithm.airforce.IterationSession;
import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class IterationResult {
    private final ContainerOrientation containerOrientation;
    private final Map<Position, ArticleOrientation> packed;
    private final Map<ArticleType, Long> articleTypeLongMap;
    private IterationSession session;

    public IterationResult(ContainerOrientation containerOrientation, Map<Position, ArticleOrientation> packed, Map<ArticleType, Long> articleTypeLongMap) {
        this.packed = packed;
        this.articleTypeLongMap = articleTypeLongMap;
        this.containerOrientation = containerOrientation;
    }

    public IterationResult(IterationSession session) {
        this.session = session;
        this.packed = session.getPackedTypes();
        this.articleTypeLongMap = session.getRemainingToPack();
        this.containerOrientation = session.containerOrientation;
    }

    public IterationSession getSession() {
        return session;
    }

    public Map<Position, ArticleOrientation> getPacked() {
        return packed;
    }

    public Map<ArticleType, Long> getArticleTypeLongMap() {
        return articleTypeLongMap;
    }

    public ContainerOrientation getContainerOrientation() {
        return containerOrientation;
    }

    public IterationResult translate() {
        Map<Position, ArticleOrientation> packed = new LinkedHashMap<>();
        for (Map.Entry<Position, ArticleOrientation> entry : this.getPacked().entrySet()) {
            Position pp = entry.getKey();
            ArticleOrientation aa = entry.getValue();
            Position p = this.getContainerOrientation().getTranslator().translate(pp);
            ArticleOrientation a = this.getContainerOrientation().getTranslator().translate(aa);
            packed.put(p, a);
//            ArticleType boxType = a.getBoxType();
//            System.out.printf("%.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f\n", boxType.getWidth(), boxType.getHeight(), boxType.getLength(), p.getX(), p.getY(), p.getZ(), a.getWidth(), a.getHeight(), a.getLength());
//            i++;
        }
        return new IterationResult(this.containerOrientation, packed, this.articleTypeLongMap);
    }

}
