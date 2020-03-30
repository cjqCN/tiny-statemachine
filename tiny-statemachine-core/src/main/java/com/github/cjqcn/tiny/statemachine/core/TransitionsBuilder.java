package com.github.cjqcn.tiny.statemachine.core;

public interface TransitionsBuilder<S, E> {
    From<S, E> from(S... stateIds);
}
