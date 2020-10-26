package com.noisyapple;

public class StateTuple {

    private String leftState;
    private String rightState;

    public StateTuple(String leftState, String rightState) {
        this.leftState = leftState;
        this.rightState = rightState;
    }

    public String getLeftState() {
        return this.leftState;
    }

    public String getRightState() {
        return this.rightState;
    }

    public String toString() {
        return "(" + leftState + ", " + rightState + ")";
    }

}
