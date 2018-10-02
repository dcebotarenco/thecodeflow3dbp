package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.algorithm.airforce.searching.SearchingService;

abstract class AbstractAction {

    private SearchingService searchingService;

    AbstractAction(SearchingService searchingService) {
        this.searchingService = searchingService;
    }

    SearchingService getSearchingService() {
        return searchingService;
    }
}
