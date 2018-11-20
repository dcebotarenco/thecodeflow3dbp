package com.codeflow.domain.algorithm.airforce;

import com.codeflow.domain.iteration.Iteration;

import java.util.List;

public class Run {

    private List<Iteration> iterations;

    public Run(List<Iteration> iterations) {
        this.iterations = iterations;
    }

    public void start() {
//        singleThreadRun();
        multiThreadRun();
    }

    void singleThreadRun() {
        for (Iteration iteration : iterations) {
            iteration.execute();
            if (iteration.isHundredPercentPacked()) {
                break;
            }
        }
    }

    void multiThreadRun() {
        iterations.parallelStream().forEach(Iteration::execute);
    }

    public List<Iteration> getIterations() {
        return iterations;
    }
}
