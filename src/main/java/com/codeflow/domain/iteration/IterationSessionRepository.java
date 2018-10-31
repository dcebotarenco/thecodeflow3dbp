package com.codeflow.domain.iteration;

import com.codeflow.domain.algorithm.airforce.IterationSession;

import java.util.LinkedList;
import java.util.List;

public class IterationSessionRepository {

    private List<IterationSession> sessions;

    public IterationSessionRepository() {
        this.sessions = new LinkedList<>();
    }

    public void save(IterationSession session) {
        this.sessions.add(session);
    }

    public List<IterationSession> getSessions() {
        return sessions;
    }
}
