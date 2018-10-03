package com.codeflow.domain.containertype.orientation;

import com.codeflow.domain.algorithm.airforce.layer.Layer;
import com.codeflow.domain.articletype.orientation.ArticleOrientation;
import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.orientation.Orientation;

public interface ContainerOrientation extends Orientation {

    Double getPackedVolume();

    Double getPackedHeight();

    Double getRemainHeight();

    Double getRemainLength();

    void pack(ArticleOrientation articleOrientation);

    void pack(Layer articleOrientation);

    boolean allVolumePacked();

    ContainerType getBoxType();

}
