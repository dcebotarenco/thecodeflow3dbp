package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.packing.PackingService;
import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.gap.GapFactory;
import com.codeflow.domain.repositoryproducer.RepositoryProducer;

public class ActionRepositoryProducer implements RepositoryProducer<ActionRepository> {

    private final SearchingService searchingService;
    private final GapFactory gapFactory;
    private final PackingService packingService;

    public ActionRepositoryProducer(SearchingService searchingService, GapFactory gapFactory, PackingService packingService) {
        this.searchingService = searchingService;
        this.gapFactory = gapFactory;
        this.packingService = packingService;
    }

    @Override
    public ActionRepository defaultRepository() {
        return new ActionRepositoryImpl(searchingService, gapFactory, packingService);
    }
}
