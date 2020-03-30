package com.github.cjqcn.tiny.statemachine.core;

public interface StateMachineManagerBuilder<S, E> {
    TransitionsBuilder<S, E> transitions();
    StateMachineManager<S, E> build();
}
