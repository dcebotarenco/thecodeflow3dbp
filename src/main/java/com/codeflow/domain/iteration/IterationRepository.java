package com.codeflow.domain.iteration;

import java.util.LinkedList;
import java.util.List;

public class IterationRepository {

    private List<Iteration> iterations;

    public IterationRepository() {
        this.iterations = new LinkedList<>();
    }

    public void save(Iteration session) {
        this.iterations.add(session);
    }

    public List<Iteration> getIterations() {
        return iterations;
    }
}
