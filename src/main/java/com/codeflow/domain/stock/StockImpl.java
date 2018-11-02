package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;
import com.codeflow.domain.articletype.ArticleTypeImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StockImpl implements Stock {

    private ArticleType articleType;
    private Long quantity;

    public StockImpl(ArticleType articleType, Long quantity) {
        this.articleType = articleType;
        this.quantity = quantity;
    }

    public StockImpl(Stock stock) {
        this.articleType = new ArticleTypeImpl(stock.getArticleType());
        this.quantity = stock.getQuantity();
    }

    public void add(Long quantity) {
        this.quantity = this.quantity + quantity;
    }

    @Override
    public void withdraw(long toWithdraw) {
        this.quantity = this.quantity - toWithdraw;
    }

    @Override
    public ArticleType getArticleType() {
        return articleType;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        StockImpl stock = (StockImpl) o;

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
}


