package com.github.cjqcn.tiny.statemachine.core.impl;

import com.github.cjqcn.tiny.statemachine.core.*;


class TransitionBuilderImpl<S, E> implements TransitionBuilder<S, E>, From<S, E>, On<S, E>, To<S, E> {

    private S from;
    private S to;
    private E event;
    private Action<S, E> action;
    private Guard<S, E> guard;

    TransitionBuilderImpl() {
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
    public From<S, E> from(S s) {
        if (this.from != null) {
            throw new IllegalStateException();
        }
        this.from = s;
        return this;
    }

    @Override
    public Transition build() {
        return new TransitionImpl(from, to, event, guard, action);
    }

    @Override
    public Transition<S, E> perform(Action<S, E> action) {
        if (this.action != null) {
            throw new IllegalStateException();
        }
        this.action = action;
        return new TransitionImpl(from, to, event, guard, this.action);
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
