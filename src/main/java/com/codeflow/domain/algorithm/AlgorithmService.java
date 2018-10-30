package com.codeflow.domain.algorithm;

public class AlgorithmService {

    public PackResult execute(Algorithm algorithm) {
        return algorithm.run();
    }
}
