package com.codeflow.domain.boxes;

/**
 * Article that has to be packed
 */
public class Article extends Box3D {

    Article(Long id, BoxType boxType) {
        super(id, boxType);
    }

    @Override
    public String toString() {
        return "Article{" + super.toString() + "}";
    }
}
