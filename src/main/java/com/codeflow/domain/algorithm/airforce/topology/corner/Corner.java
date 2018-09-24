package com.codeflow.domain.algorithm.airforce.topology.corner;

public interface Corner {
    Double getWidth();

    Double getLength();

    void updateLength(Double length);

    void updateWidth(Double width);
}
