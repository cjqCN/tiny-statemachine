package com.github.cjqcn.tiny.statemachine.core.impl;

import com.github.cjqcn.tiny.statemachine.core.From;

public class Util {

    public static <S, E> From<S, E> from(S s) {
        TransitionBuilderImpl transitionBuilder = new TransitionBuilderImpl();
        return transitionBuilder.from(s);
    }

}
