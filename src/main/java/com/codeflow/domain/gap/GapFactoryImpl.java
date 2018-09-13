package com.codeflow.domain.gap;

import com.codeflow.domain.box.Box3D;
import com.codeflow.domain.box.Box3DFactory;

import java.util.Random;

class GapFactoryImpl implements GapFactory {
    private Box3DFactory box3DFactory;

    GapFactoryImpl(Box3DFactory box3DFactory) {
        this.box3DFactory = box3DFactory;
    }

    @Override
    public Gap create(Long id, Double width, Double height, Double length) {
        Box3D box3D = box3DFactory.create(id, width, height, length);
        return new GapImpl(box3D);
    }

    @Override
    public Gap create(Double width, Double height, Double length) {
        Box3D box3D = box3DFactory.create((long) new Random().nextInt(100), width, height, length);
        return new GapImpl(box3D);
    }
}
