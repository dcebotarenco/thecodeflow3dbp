package com.codeflow.application.containertype;

import com.codeflow.application.client.Container;
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
