package com.github.cjqcn.tiny.statemachine.core;

public interface StateMachine<S, E> {

    String machineId();

    void fireEvent(E e);

    S currentState();

}
