package com.github.cjqcn.tiny.statemachine.core.impl;

import com.github.cjqcn.tiny.statemachine.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TransitionBuilderImpl<S, E> implements TransitionsBuilder<S, E>, From<S, E>, On<S, E>, To<S, E> {
    private S[] froms;
    private S to;
    private E event;
    private Action<S, E> action;
    private Guard<S, E> guard;
    private final Map<S/* from */, List<Transition<S, E>>> transitionMap;
    private final StateMachineManagerBuilder<S, E> stateMachineManagerBuilder;

    TransitionBuilderImpl(final Map<S/* from */, List<Transition<S, E>>> transitionMap, StateMachineManagerBuilder<S, E> stateMachineManagerBuilder) {
        this.transitionMap = transitionMap;
        this.stateMachineManagerBuilder = stateMachineManagerBuilder;
    }

    @Override
    public To<S, E> to(S stateId) {
        if (this.to != null) {
            throw new IllegalStateException();
        }
        this.to = stateId;
        return this;
    }

    @Override
    public When<S, E> when(Guard<S, E> guard) {
        if (this.guard != null) {
            throw new IllegalStateException();
        }
        this.guard = guard;
        return this;
    }

    @Override
    public On<S, E> on(E event) {
        if (this.event != null) {
            throw new IllegalStateException();
        }
        this.event = event;
        return this;
    }

    @Override
    public From<S, E> from(S... stateId) {
        if (this.froms != null) {
            throw new IllegalStateException();
        }
        this.froms = stateId;
        return this;
    }

    @Override
    public StateMachineManagerBuilder<S, E> perform(Action<S, E> action) {
        if (this.action != null) {
            throw new IllegalStateException();
        }
        this.action = action;
        for (S from : froms) {
            List<Transition<S, E>> temp = new ArrayList<>();
            List<Transition<S, E>> transitions = transitionMap.putIfAbsent(from, temp);
            if (transitions == null) {
                transitions = temp;
            }
            transitions.add(new TransitionImpl(from, to, event, guard, this.action));
        }
        return stateMachineManagerBuilder;
    }


    class TransitionImpl<S, E> implements Transition<S, E> {
        private final S from;
        private final S to;
        private final E event;
        private final Guard<S, E> guard;
        private final Action<S, E> action;

        TransitionImpl(S from, S to, E event, Guard<S, E> guard, Action<S, E> action) {
            this.from = from;
            this.to = to;
            this.event = event;
            this.guard = guard;
            this.action = action;
        }

        @Override
        public S from() {
            return from;
        }

        @Override
        public S to() {
            return to;
        }

        @Override
        public E event() {
            return event;
        }

        @Override
        public Guard<S, E> guard() {
            return guard;
        }

        @Override
        public Action<S, E> action() {
            return action;
        }
    }

}
