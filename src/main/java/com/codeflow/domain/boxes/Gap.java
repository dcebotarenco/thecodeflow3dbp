package com.codeflow.domain.boxes;

import com.codeflow.domain.boxes.Box3D;
import com.codeflow.domain.boxes.BoxType;
import com.codeflow.domain.boxes.Orientation;

public class Gap extends Box3D {

    Gap(Long id, BoxType boxType) {
        super(id, boxType);
    }

    public boolean fit(Orientation orientation) {
        return orientation.getWidth() <= this.getWidth() &&
                orientation.getHeight() <= this.getHeight() && orientation.getLength() <= this.getLength();
    }

    public boolean smallerThenHeight(Orientation orientation) {
        return orientation.getHeight() <= this.getDimensions().getHeight();
    }

}
