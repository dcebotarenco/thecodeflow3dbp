package com.codeflow.application;

import com.codeflow.domain.algorithm.Result;

public class PackResult {
    private Result result;

    public PackResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
