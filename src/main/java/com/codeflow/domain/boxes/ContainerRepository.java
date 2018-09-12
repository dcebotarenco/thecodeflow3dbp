package com.codeflow.domain.boxes;

public class ContainerRepository {

    Container receivedContainer;

    public ContainerRepository() {
    }

    public void save(Container container) {
        this.receivedContainer = container;
    }

    public Container container() {
        return receivedContainer;
    }

    public void clear() {
        receivedContainer = null;
    }


}
