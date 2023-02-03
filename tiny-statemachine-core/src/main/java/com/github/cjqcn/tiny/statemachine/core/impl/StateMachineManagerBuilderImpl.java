package com.github.cjqcn.tiny.statemachine.core.impl;

import com.github.cjqcn.tiny.statemachine.core.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachineManagerBuilderImpl<S, E> implements StateMachineManagerBuilder<S, E> {
    private final String id;
    private final S initialState;
    private Map<S/* from */, List<Transition<S, E>>> transitionMap;
    private StateMachineManager<S, E> stateMachineManager;
    private volatile boolean init = false;

    public StateMachineManagerBuilderImpl(String id, S initialState) {
        this.id = id;
        this.initialState = initialState;
        this.transitionMap = new ConcurrentHashMap<>();
    }

    @Override
    public StateMachineManagerBuilder<S, E> addTransition(Transition<S, E> transition) {
        checkStatus();
        S from = transition.from();
        List<Transition<S, E>> transitions = transitionMap.get(from);
        if (transitions == null) {
            transitions = new ArrayList<>();
            transitionMap.put(from, transitions);
        }
        transitions.add(transition);
        return this;
    }

    private synchronized void checkStatus() {
        if (init) {
            throw new IllegalStateException();
        }
    }

    @Override
    public synchronized StateMachineManager<S, E> build() {
        init = true;
        transitionMap = Collections.unmodifiableMap(transitionMap);
        stateMachineManager = new StateMachineManagerImpl<>();
        return stateMachineManager;
    }

    class StateMachineManagerImpl<S, E> implements StateMachineManager<S, E> {
        private Map<String/*machineId*/, StateMachine<S, E>> stateMachineMap = new ConcurrentHashMap<>();

        @Override
        public String id() {
            return id;
        }

        @Override
        public StateMachine<S, E> get(String machineId) {
            return stateMachineMap.get(machineId);
        }

        @Override
        public StateMachine<S, E> newInstance() {
            return newInstance(UUID.randomUUID().toString());
        }

        @Override
        public StateMachine<S, E> newInstance(String machineId) {
            StateMachine<S, E> stateMachine = new StateMachineImpl(machineId, initialState, transitionMap);
            StateMachine<S, E> oldStateMachine = stateMachineMap.putIfAbsent(machineId, stateMachine);
            if (oldStateMachine != null) {
                throw new IllegalStateException();
            }
            return stateMachine;
        }
    }
}
