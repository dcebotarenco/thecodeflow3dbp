package com.codeflow.application.client;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Input {
    Container container;
    List<ArticleType> articleTypeDTOList;

    public Input(Container container, List<ArticleType> articleTypeDTOList) {
        this.container = container;
        this.articleTypeDTOList = articleTypeDTOList;
    }

    public Container getContainer() {
        return container;
    }

    public List<ArticleType> getArticleTypeDTOList() {
        return articleTypeDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        return new EqualsBuilder()
                .append(container, input.container)
                .append(articleTypeDTOList, input.articleTypeDTOList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(container)
                .append(articleTypeDTOList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Input{" +
                "container=" + container +
                ", articleDTOList=" + articleTypeDTOList +
                '}';
    }
}
