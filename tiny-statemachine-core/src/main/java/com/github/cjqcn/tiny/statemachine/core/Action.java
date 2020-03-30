package com.github.cjqcn.tiny.statemachine.core;

import java.util.Objects;

@FunctionalInterface
public interface Action<S, E> {
    void execute(S from, S to, E event);

    default Action<S, E> andThen(Action<S, E> after) {
        Objects.requireNonNull(after);
        return (from, to, event) -> {
            execute(from, to, event);
            after.execute(from, to, event);
        };
    }
}
