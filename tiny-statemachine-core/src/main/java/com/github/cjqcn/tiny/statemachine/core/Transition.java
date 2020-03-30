package com.github.cjqcn.tiny.statemachine.core;

public interface Transition<S, E> {
    S from();

    S to();

    E event();

    Guard<S, E> guard();

    Action<S, E> action();

}
