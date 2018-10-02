package com.codeflow.application;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class InputDTO {
    ContainerDTO containerDTO;
    List<ArticleTypeDTO> articleTypeDTOList;

    public InputDTO(ContainerDTO containerDTO, List<ArticleTypeDTO> articleTypeDTOList) {
        this.containerDTO = containerDTO;
        this.articleTypeDTOList = articleTypeDTOList;
    }

    public ContainerDTO getContainerDTO() {
        return containerDTO;
    }

    public List<ArticleTypeDTO> getArticleTypeDTOList() {
        return articleTypeDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        InputDTO inputDTO = (InputDTO) o;

        return new EqualsBuilder()
                .append(containerDTO, inputDTO.containerDTO)
                .append(articleTypeDTOList, inputDTO.articleTypeDTOList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(containerDTO)
                .append(articleTypeDTOList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "InputDTO{" +
                "containerDTO=" + containerDTO +
                ", articleDTOList=" + articleTypeDTOList +
                '}';
    }
}
