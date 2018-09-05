package com.codeflow.domain.algorithm;

import com.codeflow.domain.boxes.Article;
import com.codeflow.domain.boxes.Container;

import java.util.List;

public class AlgorithmService {

    public Result execute(Algorithm algorithm, Container container, List<Article> articles) {
        return algorithm.run(container, articles);
    }
}
