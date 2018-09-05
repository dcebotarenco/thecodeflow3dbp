package com.codeflow.domain.container;

public class ContainerFactory {
    public Container createContainer(Long id, Double width, Double length, Double height) {
        return new Container(id, width, length, height);
    }
}
