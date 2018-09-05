package com.codeflow.domain.algorithm;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.Container;

import java.util.List;

public class AlgorithmService {

    public Result execute(Algorithm algorithm, Container container, List<Article> articles) {
        return algorithm.run(container, articles);
    }
}
