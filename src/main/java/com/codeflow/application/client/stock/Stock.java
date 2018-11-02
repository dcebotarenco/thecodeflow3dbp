package com.codeflow.application.client.stock;

import com.codeflow.application.client.ArticleType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Stock {
    private ArticleType articleType;
    private Long quantity;

    public Stock(ArticleType articleType, Long quantity) {
        this.articleType = articleType;
        this.quantity = quantity;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        return new EqualsBuilder()
                .append(articleType, stock.articleType)
                .append(quantity, stock.quantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(articleType)
                .append(quantity)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Stock{" +
                "articleType=" + articleType +
                ", quantity=" + quantity +
                '}';
    }
}
