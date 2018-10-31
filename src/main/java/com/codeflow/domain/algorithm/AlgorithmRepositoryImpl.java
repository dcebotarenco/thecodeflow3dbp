package com.codeflow.domain.algorithm;

import com.codeflow.domain.algorithm.airforce.AirForceAlgorithm;

public class AlgorithmRepositoryImpl implements AlgorithmRepository<Algorithm> {

    @Override
    public Algorithm getAirForceAlgorithm() {
        return new AirForceAlgorithm();
    }
}
