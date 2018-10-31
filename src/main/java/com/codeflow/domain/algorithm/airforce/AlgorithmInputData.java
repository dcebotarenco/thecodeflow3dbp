package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.containertype.ContainerType;

import java.util.Map;

public class AlgorithmInputData {


    private ContainerType containerType;
    private Map<ArticleType, Long> articleTypes;

    AlgorithmInputData(ContainerType containerType, Map<ArticleType, Long> articleTypes) {
        this.containerType = containerType;
        this.articleTypes = articleTypes;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public Map<ArticleType, Long> getArticleTypes() {
        return articleTypes;
    }
}
