package com.codeflow.domain.boxes;

import java.util.List;

/**
 * Article that has to be packed
 */
public class Article extends Box3D {

    Article(Long id, Dimensions3D dimensions3D, List<Orientation> orientations) {
        super(id, dimensions3D, orientations);
    }

    @Override
    public String toString() {
        return "Article{" + super.toString() + "}";
    }
}
