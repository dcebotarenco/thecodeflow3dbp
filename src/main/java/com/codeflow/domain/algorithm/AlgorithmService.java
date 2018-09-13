package com.codeflow.domain.algorithm;

public class AlgorithmService {

    public Result execute(Algorithm algorithm) {
        return algorithm.run();
    }
}
