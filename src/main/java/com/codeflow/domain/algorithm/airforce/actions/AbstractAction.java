package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.searching.SearchingService;
import com.codeflow.domain.gap.GapFactory;

abstract class AbstractAction {

    private SearchingService searchingService;
    private GapFactory gapFactory;

    AbstractAction(SearchingService searchingService, GapFactory gapFactory) {
        this.searchingService = searchingService;
        this.gapFactory = gapFactory;
    }

    SearchingService getSearchingService() {
        return searchingService;
    }

    GapFactory getGapFactoryImpl() {
        return gapFactory;
    }
}
