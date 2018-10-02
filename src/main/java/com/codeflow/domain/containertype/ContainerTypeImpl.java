package com.codeflow.domain.containertype;

import com.codeflow.domain.boxtype.BoxType;
import com.codeflow.domain.boxtype.BoxTypeImpl;
import com.codeflow.domain.containertype.orientation.ContainerOrientation;
import com.codeflow.domain.containertype.orientation.ContainerOrientationImpl;

import java.util.List;

/**
 * Container in what the Articles have to be packed.
 */
public class ContainerTypeImpl implements ContainerType {

    private BoxType<ContainerOrientation> boxType;

    public ContainerTypeImpl(Double width, Double height, Double length) {
        this.boxType = new BoxTypeImpl<>(width, height, length);
        /*As it is*/
        boxType.add(new ContainerOrientationImpl(width, height, length, this));
        /*Rotate on Y*/
        boxType.add(new ContainerOrientationImpl(length, height, width, this));
        /*Rotate on Z&Y*/
        boxType.add(new ContainerOrientationImpl(length, width, height, this));
        /*Rotate on Z*/
        boxType.add(new ContainerOrientationImpl(height, width, length, this));
        /*Rotate on X*/
        boxType.add(new ContainerOrientationImpl(width, length, height, this));
        /*Rotate on X&Y*/
        boxType.add(new ContainerOrientationImpl(height, length, width, this));
    }

    @Override
    public Double getWidth() {
        return boxType.getWidth();
    }

    @Override
    public Double getLength() {
        return boxType.getLength();
    }

    @Override
    public Double getHeight() {
        return boxType.getHeight();
    }

    @Override
    public Double getVolume() {
        return boxType.getVolume();
    }

    @Override
    public List<ContainerOrientation> getOrientations() {
        return boxType.getOrientations();
    }

    @Override
    public void add(ContainerOrientation orientation) {
        this.boxType.add(orientation);
    }
}
