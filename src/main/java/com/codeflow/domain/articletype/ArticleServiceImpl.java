package com.codeflow.domain.articletype;

import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleTypeRepository articleTypeRepository;
    private Map<Position, ArticleOrientation> packedTypes;

    public ArticleServiceImpl(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
        this.packedTypes = new HashMap<>();
    }

    @Override
    public void pack(ArticleOrientation articleOrientation, Position position) {
        articleTypeRepository.savePack(articleOrientation.getBoxType());
        packedTypes.put(position, articleOrientation);
    }

    @Override
    public boolean allPacked() {
        return articleTypeRepository.areAllPacked();
    }

    @Override
    public Double totalItemVolume() {
        return articleTypeRepository.receivedArticleTypes().entrySet().stream().map(t -> t.getKey().getVolume() * t.getValue()).reduce((v1, v2) -> v1 + v2).orElse(0D);
    }

    @Override
    public void reset() {
        packedTypes.clear();
        articleTypeRepository.reset();
    }

    @Override
    public Map<ArticleType, Long> articleTypes() {
        return articleTypeRepository.receivedArticleTypes();
    }

    @Override
    public List<ArticleType> unpackedTypes() {
        return articleTypeRepository.unpackedTypes();
    }

    @Override
    public Map<ArticleType, Long> remainingToPack() {
        return articleTypeRepository.remainingToPack();
    }
}
