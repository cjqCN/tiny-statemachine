package com.github.cjqcn.tiny.statemachine.core;


import static com.github.cjqcn.tiny.statemachine.core.impl.Util.from;

import com.github.cjqcn.tiny.statemachine.core.impl.StateMachineManagerBuilderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateMachineTest {

    private StateMachineManager<States, Events> machineManager;

    @Before
    public void setUp() {
        StateMachineManagerBuilder builder = new StateMachineManagerBuilderImpl("xxxx1", States.STATE1);
        machineManager = builder
            .addTransition(
                from(States.STATE1)
                    .to(States.STATE2)
                    .on(Events.EVENT1)
                    .when((from, To, event) -> true)
                    .perform((from, to, event) -> System.out.println(from + " " + to + " " + event))
            )
            .addTransition(
                from(States.STATE2)
                    .to(States.STATE3)
                    .on(Events.EVENT2)
                    .when((from, To, event) -> true)
                    .perform((from, to, event) -> System.out.println(from + " " + to + " " + event))
            )
            .addTransition(
                from(States.STATE3)
                    .to(States.STATE4)
                    .on(Events.EVENT3)
                    .when((from, To, event) -> true)
                    .perform((from, to, event) -> System.out.println(from + " " + to + " " + event))
            )
            .addTransition(
                from(States.STATE4)
                    .to(States.STATE1)
                    .on(Events.EVENT4)
                    .when((from, To, event) -> true)
                    .perform((from, to, event) -> System.out.println(from + " " + to + " " + event))
            )
            .build();
    }


    @Test
    public void test() {
        StateMachine stateMachine = machineManager.newInstance("xxxx1");
        Assert.assertEquals(States.STATE1, stateMachine.currentState());
        stateMachine.fireEvent(Events.EVENT1);
        stateMachine.fireEvent(Events.EVENT2);
        stateMachine.fireEvent(Events.EVENT3);
        Assert.assertEquals(States.STATE4, stateMachine.currentState());

        stateMachine = machineManager.newInstance("xxxx2");
        stateMachine.fireEvent(Events.EVENT1);
        stateMachine.fireEvent(Events.EVENT2);
        stateMachine.fireEvent(Events.EVENT3);
        Assert.assertEquals(States.STATE4, stateMachine.currentState());

        stateMachine = machineManager.get("xxxx1");
        Assert.assertEquals(States.STATE4, stateMachine.currentState());
        stateMachine.fireEvent(Events.EVENT4);
        Assert.assertEquals(States.STATE1, stateMachine.currentState());
    }


    enum States {
        STATE1, STATE2, STATE3, STATE4
    }

    enum Events {
        EVENT1, EVENT2, EVENT3, EVENT4
    }

}