package com.codeflow.domain.algorithm;

import com.codeflow.domain.boxes.Article;
import com.codeflow.domain.boxes.Container;

import java.util.List;

public interface Algorithm {

    Result run(Container container, List<Article> articles);
}
