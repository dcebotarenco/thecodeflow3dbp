package com.codeflow.domain.containertype.orientation;

import com.codeflow.domain.containertype.ContainerType;
import com.codeflow.domain.orientation.Orientation;
import com.codeflow.domain.translator.Translator;

public interface ContainerOrientation extends Orientation {

    Double getPackedHeight();

    Double getRemainHeight();

    Double getRemainLength();

    void pack(Double height);

    Translator getTranslator();

    ContainerType getBoxType();


}
