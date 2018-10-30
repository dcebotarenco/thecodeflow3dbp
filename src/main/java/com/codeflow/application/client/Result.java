package com.codeflow.application.client;

import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Placement> placementList;

    Result(List<Placement> placementList) {
        this.placementList = placementList;
    }

    @Override
    public String toString() {
        return placementList.stream().map(Placement::toString).collect(Collectors.joining("\r\n"));
    }
}
