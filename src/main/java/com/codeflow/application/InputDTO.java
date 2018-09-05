package com.codeflow.application;

import java.util.List;

public class InputDTO {
    ContainerDTO containerDTO;
    List<ArticleDTO> articleDTOList;

    public InputDTO(ContainerDTO containerDTO, List<ArticleDTO> articleDTOList) {
        this.containerDTO = containerDTO;
        this.articleDTOList = articleDTOList;
    }

    public ContainerDTO getContainerDTO() {
        return containerDTO;
    }

    public List<ArticleDTO> getArticleDTOList() {
        return articleDTOList;
    }
}
