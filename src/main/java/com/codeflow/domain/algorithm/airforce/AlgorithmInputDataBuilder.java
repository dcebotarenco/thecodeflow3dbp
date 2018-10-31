package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.containertype.ContainerType;

import java.util.Map;

public class AlgorithmInputDataBuilder {
    private ContainerType containerType;
    private Map<ArticleType, Long> articleTypes;

    public AlgorithmInputDataBuilder setContainerType(ContainerType containerType) {
        this.containerType = containerType;
        return this;
    }

    public AlgorithmInputDataBuilder setArticleTypes(Map<ArticleType, Long> articleTypes) {
        this.articleTypes = articleTypes;
        return this;
    }

    public AlgorithmInputData createAlgorithmInputData() {
        return new AlgorithmInputData(containerType, articleTypes);
    }
}