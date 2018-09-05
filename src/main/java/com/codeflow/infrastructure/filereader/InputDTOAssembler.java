package com.codeflow.infrastructure.filereader;

import com.codeflow.application.ArticleDTO;
import com.codeflow.application.ContainerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class InputDTOAssembler {
    ContainerDTO createContainer(Long id, List<String> containerLine) {
        return new ContainerDTO(Long.valueOf(id),
                Double.valueOf(containerLine.get(0)),
                Double.valueOf(containerLine.get(1)),
                Double.valueOf(containerLine.get(2)));
    }

    ArticleDTO createArticle(List<String> articleLine) {
        return new ArticleDTO(Long.valueOf(articleLine.get(0)),
                Double.valueOf(articleLine.get(1)),
                Double.valueOf(articleLine.get(2)),
                Double.valueOf(articleLine.get(3)));
    }

    List<ArticleDTO> createArticles(List<List<String>> articleLines) {
        return articleLines.stream().map(this::createArticle).collect(Collectors.toList());
    }
}
