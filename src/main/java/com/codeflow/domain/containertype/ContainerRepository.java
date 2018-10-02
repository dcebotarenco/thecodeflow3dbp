package com.codeflow.domain.containertype;

public interface ContainerRepository {
    void save(ContainerType container);

    ContainerType container();

    void clear();
}
