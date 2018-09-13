package com.codeflow.domain.algorithm.airforce.searching;

import com.codeflow.domain.gap.Gap;

public interface SearchingService {

    SearchResult findBoxTypes(Gap requiredGapImpl, Gap maxGapImpl);
}
