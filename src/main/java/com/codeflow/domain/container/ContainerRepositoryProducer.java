package com.codeflow.domain.container;

import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class ContainerRepositoryProducer implements RepositoryProducer<ContainerRepository> {
    @Override
    public ContainerRepository defaultRepository() {
        return new ContainerRepositoryImpl();
    }
}
