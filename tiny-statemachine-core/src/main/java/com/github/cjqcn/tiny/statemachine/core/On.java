package com.github.cjqcn.tiny.statemachine.core;

public interface On<S, E> extends When<S, E>{

    When<S, E> when(Guard<S, E> condition);
}
