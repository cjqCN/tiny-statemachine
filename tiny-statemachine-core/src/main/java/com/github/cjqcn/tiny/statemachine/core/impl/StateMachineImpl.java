package com.github.cjqcn.tiny.statemachine.core.impl;

import com.github.cjqcn.tiny.statemachine.core.StateMachine;
import com.github.cjqcn.tiny.statemachine.core.Transition;

import java.util.List;
import java.util.Map;

public class StateMachineImpl<S, E> implements StateMachine<S, E> {
    private final String machineId;
    private final S initialState;
    private volatile S currentState;
    private final Map<S/* from */, List<Transition<S, E>>> transitionMap;

    public StateMachineImpl(String machineId, S initialState, final Map<S/* from */, List<Transition<S, E>>> transitionMap) {
        this.machineId = machineId;
        this.initialState = initialState;
        this.currentState = initialState;
        this.transitionMap = transitionMap;
    }

    @Override
    public String machineId() {
        return machineId;
    }

    @Override
    public void fireEvent(E e) {
        List<Transition<S, E>> transitions = transitionMap.get(currentState);
        for (Transition<S, E> transition : transitions) {
            if (transition.event().equals(e)) {
                if (transition.guard() != null && !transition.guard().evaluate(currentState, transition.to(), e)) {
                    continue;
                }
                if (transition.action() != null) {
                    transition.action().execute(currentState, transition.to(), e);
                }
                currentState = transition.to();
                break;
            }
        }
    }

    @Override
    public S currentState() {
        return currentState;
    }
}
