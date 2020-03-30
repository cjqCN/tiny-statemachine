package com.github.cjqcn.tiny.statemachine.core;

public interface StateMachineManager<S, E> {

    String id();

    StateMachine<S, E> get(String machineId);

    StateMachine<S, E> newInstance();

    StateMachine<S, E> newInstance(String machineId);


}
