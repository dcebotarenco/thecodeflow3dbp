package com.codeflow.domain.stock;

import com.codeflow.domain.articletype.ArticleType;

public interface Stock {

    ArticleType getArticleType();

    Long getQuantity();

    void add(Long toAdd);

    void withdraw(long toWithdraw);
}
