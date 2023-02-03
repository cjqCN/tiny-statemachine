package com.github.cjqcn.tiny.statemachine.core;

public interface From<S, E> {
    To<S, E> to(S stateId);
}
