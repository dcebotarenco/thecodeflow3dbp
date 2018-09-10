package com.codeflow.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InputDTO inputDTO = (InputDTO) o;

        return new EqualsBuilder()
                .append(containerDTO, inputDTO.containerDTO)
                .append(articleDTOList, inputDTO.articleDTOList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(containerDTO)
                .append(articleDTOList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InputDTO{" +
                "containerDTO=" + containerDTO +
                ", articleDTOList=" + articleDTOList +
                '}';
    }
}
