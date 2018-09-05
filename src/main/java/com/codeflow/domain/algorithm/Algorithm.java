package com.codeflow.domain.algorithm;

import com.codeflow.domain.article.Article;
import com.codeflow.domain.container.Container;

import java.util.List;

public interface Algorithm {

    Result run(Container container, List<Article> articles);
}
