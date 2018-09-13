package com.codeflow.domain.container;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.box.Box3DFactory;

import java.util.Random;

class ContainerFactoryImpl implements ContainerFactory {

    private Box3DFactory box3DFactory;

    ContainerFactoryImpl(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }


    @Override
    public Container create(Long id, Double width, Double height, Double length) {
        Box3D box3D = box3DFactory.create(id, width, height, length);
        return new ContainerImpl(box3D);
    }

    @Override
    public Container create(Double width, Double height, Double length) {
        Box3D box3D = box3DFactory.create((long) new Random().nextInt(100), width, height, length);
        return new ContainerImpl(box3D);
    }
}
