package com.codeflow.domain.container;

public interface ContainerRepository {
    void save(Container container);

    Container container();

    void clear();
}
