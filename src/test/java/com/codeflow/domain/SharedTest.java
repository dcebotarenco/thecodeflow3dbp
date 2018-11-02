package com.codeflow.domain;

import com.codeflow.domain.gap.Gap;
import com.codeflow.domain.gap.GapImpl;

public class SharedTest {




    public static Gap gap(Integer w, Integer h, Integer l) {
        return new GapImpl(w.doubleValue(), h.doubleValue(), l.doubleValue());
    }

    public static Gap gap(Double w, Double h, Double l) {
        return new GapImpl(w, h, l);
    }

}
