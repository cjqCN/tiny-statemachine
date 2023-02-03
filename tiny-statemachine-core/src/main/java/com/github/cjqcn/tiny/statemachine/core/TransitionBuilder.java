package com.github.cjqcn.tiny.statemachine.core;


public interface TransitionBuilder<S, E> {
    From<S, E> from(S s);

    Transition build();

}
