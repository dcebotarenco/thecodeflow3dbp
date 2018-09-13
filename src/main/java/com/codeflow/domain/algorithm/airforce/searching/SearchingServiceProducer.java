package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.boxtype.BoxTypeRepository;
import com.codeflow.domain.serviceproducer.ServiceProducer;

public class SearchingServiceProducer implements ServiceProducer<SearchingService> {

    private BoxTypeRepository boxTypeRepository;

    public SearchingServiceProducer(BoxTypeRepository boxTypeRepository) {
        this.boxTypeRepository = boxTypeRepository;
    }

    @Override
    public SearchingService defaultService() {
        return new SearchingServiceImpl(boxTypeRepository);
    }
}
