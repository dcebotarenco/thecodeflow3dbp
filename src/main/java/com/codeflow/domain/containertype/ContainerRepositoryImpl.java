package com.codeflow.domain.containertype;

public class ContainerRepositoryImpl implements ContainerRepository {

    private ContainerType receivedContainer;

    @Override
    public void save(ContainerType container) {
        this.receivedContainer = container;
    }

    @Override
    public ContainerType container() {
        return receivedContainer;
    }

    @Override
    public void clear() {
        receivedContainer = null;
    }

}
