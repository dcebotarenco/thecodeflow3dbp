package com.codeflow.application.client;

public class Placement {

    private ArticleType articleType;
    private Position position;
    private Orientation orientation;

    public Placement(ArticleType articleType, Position position, Orientation orientation) {
        this.articleType = articleType;
        this.position = position;
        this.orientation = orientation;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return String.format("%.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f %.0f", articleType.getWidth(),
                articleType.getHeight(),
                articleType.getLength(),
                position.getX(), position.getY(), position.getZ(),
                orientation.getWidth(), orientation.getHeight(), orientation.getLength());
    }
}
