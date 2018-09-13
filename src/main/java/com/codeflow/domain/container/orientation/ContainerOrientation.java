package com.codeflow.domain.container.orientation;

import com.codeflow.domain.article.orientation.ArticleOrientation;
import com.codeflow.domain.orientation.Orientation;

public interface ContainerOrientation extends Orientation {

    Double getPackedVolume();

    Double getPackedHeight();

    Double getRemainHeight();

    Double getRemainLength();

    void pack(ArticleOrientation articleOrientation);

    boolean allVolumePacked();

}
