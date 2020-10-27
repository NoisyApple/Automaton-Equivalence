package com.noisyapple;

// Models a transition with an origin and final state and a transition value between.
public class Transition {

    private String originState;
    private char transitionValue;
    private String finalState;

    public Transition(String originState, char transitionValue, String finalState) {
        this.originState = originState;
        this.transitionValue = transitionValue;
        this.finalState = finalState;
    }

    // Prints the characteristics of the transition.
    public String toString() {
        return "{" + originState + ", " + transitionValue + ", " + finalState + "}";
    }

    // GETTERS +++
    public String getOriginState() {
        return originState;
    }

    public char getTransitionValue() {
        return transitionValue;
    }

    public String getFinalState() {
        return finalState;
    }
    // GETTERS ---

    // SETTERS +++
    public void setOriginState(String originState) {
        this.originState = originState;
    }

    public void setTransitionValue(char transitionValue) {
        this.transitionValue = transitionValue;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }
    // SETTERS ---

}
