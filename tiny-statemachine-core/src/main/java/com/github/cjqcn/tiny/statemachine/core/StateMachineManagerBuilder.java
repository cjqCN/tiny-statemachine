package com.github.cjqcn.tiny.statemachine.core;

public interface StateMachineManagerBuilder<S, E> {

    StateMachineManagerBuilder<S, E> addTransition(Transition<S, E> t);

    StateMachineManager<S, E> build();
}
