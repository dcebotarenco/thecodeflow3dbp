package com.codeflow.application.client;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Input {
    Container container;
    List<ArticleType> articleTypes;

    public Input(Container container, List<ArticleType> articleTypes) {
        this.container = container;
        this.articleTypes = articleTypes;
    }

    public Container getContainer() {
        return container;
    }

    public List<ArticleType> getArticleTypes() {
        return articleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        return new EqualsBuilder()
                .append(container, input.container)
                .append(articleTypes, input.articleTypes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(container)
                .append(articleTypes)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Input{" +
                "container=" + container +
                ", articleDTOList=" + articleTypes +
                '}';
    }
}
