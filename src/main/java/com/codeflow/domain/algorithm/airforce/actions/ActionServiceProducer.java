package com.codeflow.domain.algorithm.airforce.actions;

import com.codeflow.domain.serviceproducer.ServiceProducer;

public class ActionServiceProducer implements ServiceProducer<ActionService> {

    private ActionRepository actionRepository;

    public ActionServiceProducer(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public ActionService defaultService() {
        return new ActionServiceImpl(actionRepository);
    }
}
