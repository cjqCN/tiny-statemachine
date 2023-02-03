package com.github.cjqcn.tiny.statemachine.core;

public interface When<S, E> {
    Transition<S, E> perform(Action<S, E> action);
}
