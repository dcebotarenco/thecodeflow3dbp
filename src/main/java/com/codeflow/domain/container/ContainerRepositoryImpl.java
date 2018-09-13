package com.codeflow.domain.container;

class ContainerRepositoryImpl implements ContainerRepository {

    private Container receivedContainer;

    @Override
    public void save(Container container) {
        this.receivedContainer = container;
    }

    @Override
    public Container container() {
        return receivedContainer;
    }

    @Override
    public void clear() {
        receivedContainer = null;
    }


}
