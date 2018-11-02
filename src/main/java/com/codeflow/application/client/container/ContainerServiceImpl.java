package com.codeflow.application.client.container;

import com.codeflow.domain.containertype.ContainerRepository;
import com.codeflow.domain.containertype.ContainerTypeImpl;

public class ContainerServiceImpl implements ContainerService {
    ContainerRepository containerRepository;

    public ContainerServiceImpl(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public void create(Container container) {
        containerRepository.save(new ContainerTypeImpl(container.getWidth(),
                container.getHeight(),
                container.getLength()));
    }


}
