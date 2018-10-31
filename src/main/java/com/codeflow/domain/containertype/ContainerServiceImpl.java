package com.codeflow.domain.containertype;

public class ContainerServiceImpl implements ContainerService {

    ContainerRepository containerRepository;

    public ContainerServiceImpl(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public ContainerType container() {
        return containerRepository.container();
    }
}
