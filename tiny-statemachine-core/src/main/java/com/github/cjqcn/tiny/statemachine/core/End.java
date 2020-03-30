package com.github.cjqcn.tiny.statemachine.core;

public interface End<S, E> {
    From<S, E> and();

    StateMachineManager<S, E> build();
}
