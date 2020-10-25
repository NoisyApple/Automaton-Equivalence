package com.noisyapple;

// Models a transition with an origin and final state and a transition value between.
public class Transition {

    String originState;
    char transitionValue;
    String finalState;

    public Transition(String originState, char transitionValue, String finalState) {
        this.originState = originState;
        this.transitionValue = transitionValue;
        this.finalState = finalState;
    }

    // Prints the characteristics of the transition.
    public String toString() {
        return "{" + originState + ", " + transitionValue + ", " + finalState + "}";
    }

}
