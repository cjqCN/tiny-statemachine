package com.github.cjqcn.tiny.statemachine.core;

import java.util.Objects;

@FunctionalInterface
public interface Guard<S, E> {
    boolean evaluate(S from, S To, E event);

    default Guard<S, E> and(Guard<S, E> other) {
        Objects.requireNonNull(other);
        return (from, to, event) -> evaluate(from, to, event) && other.evaluate(from, to, event);
    }

    default Guard<S, E> negate() {
        return (from, to, event) -> !evaluate(from, to, event);
    }

    default Guard<S, E> or(Guard<S, E> other) {
        Objects.requireNonNull(other);
        return (from, to, event) -> evaluate(from, to, event) || other.evaluate(from, to, event);
    }


}
