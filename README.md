# tiny-statemachine
## 创建一个状态机管理者
```
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
```

## 初始化一个状态机
```
    StateMachine stateMachine = machineManager.newInstance("xxxx1");
```

## 发送事件
```
    stateMachine.fireEvent(Events.EVENT1);
    stateMachine.fireEvent(Events.EVENT2);
    stateMachine.fireEvent(Events.EVENT3);
```