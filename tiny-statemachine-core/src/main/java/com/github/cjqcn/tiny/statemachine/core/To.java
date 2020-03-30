package com.github.cjqcn.tiny.statemachine.core;

public interface To<S, E> {

    On<S, E> on(E event);
}
