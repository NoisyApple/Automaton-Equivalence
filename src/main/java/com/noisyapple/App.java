package com.noisyapple;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        String[] cases = {"10", "010110", "", "01"};

        // String[] states = {"q0", "q1"}; // Q.
        // String alphabet = "01"; // Sigma.
        // String startS = "q0"; // q0.
        // Transition[] transitions = {new Transition("q0", '0', "q1"),
        // new Transition("q1", '0', "q0"), new Transition("q1", '1', "q1")}; // Delta.
        // String[] acceptStates = {"q1", "q0"}; // F.

        // DFA M = new DFA(states, alphabet, startS, transitions, acceptStates);

        // System.out.println(M.toString());
        // System.out.println(M.evaluate("011"));

        String alphabet = "01"; // Sigma.

        String[] Q1 = {"i", "0", "1", "00", "01", "10", "11"}; // Q.
        String q0_1 = "i"; // q0.
        Transition[] trans_1 = {new Transition("i", '0', "0"), new Transition("i", '1', "1"),
                new Transition("0", '0', "00"), new Transition("0", '1', "01"),
                new Transition("1", '0', "10"), new Transition("1", '1', "11"),
                new Transition("00", '0', "00"), new Transition("00", '1', "01"),
                new Transition("01", '0', "10"), new Transition("01", '1', "11"),
                new Transition("10", '0', "00"), new Transition("10", '1', "01"),
                new Transition("11", '0', "10"), new Transition("11", '1', "11")}; // Delta.
        String[] F1 = {"10"}; // F.

        DFA M1 = new DFA(Q1, alphabet, q0_1, trans_1, F1); // (0+1)*10

        String[] Q2 = {"A", "B", "C"}; // Q.
        String q0_2 = "A"; // q0.
        Transition[] trans_2 = {new Transition("A", '0', "A"), new Transition("A", '1', "B"),
                new Transition("B", '1', "B"), new Transition("B", '0', "C"),
                new Transition("C", '1', "B"), new Transition("C", '0', "A"),}; // Delta.
        String[] F2 = {"C"}; // F.

        DFA M2 = new DFA(Q2, alphabet, q0_2, trans_2, F2); // (0+1)*10

        System.out.println(M1.toString());
        System.out.println(M2.toString());

        for (String c : cases) {
            System.out.println("[TEST SEQUENCE] => \"" + c + "\"");
            System.out.println("M1 => " + M1.evaluate(c));
            System.out.println("M2 => " + M2.evaluate(c) + "\n");
        }
    }

}
