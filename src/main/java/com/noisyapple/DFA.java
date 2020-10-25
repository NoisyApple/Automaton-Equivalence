package com.noisyapple;

import java.util.Arrays;

public class DFA {

    String[] states; // Q.
    String alphabet; // Sigma.
    String startS; // q0.
    Transition[] transitions; // Delta.
    String[] acceptStates; // F.

    public DFA(String[] states, String alphabet, String startS, Transition[] transitions,
            String[] acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.startS = startS;
        this.transitions = transitions;
        this.acceptStates = acceptStates;
    }

    // Evaluates a sequence with the automaton.
    public boolean evaluate(String sequence) {

        Transition transition = null;

        // Iterates through each character from the sequence.
        while (sequence.length() > 0) {

            // Sets the actual state based on the transition's finalState or the start state.
            String actualState = (transition != null) ? transition.finalState : startS;
            char actualCharacter = sequence.charAt(0);

            // Try catch block prevents array index out of bounds error.
            try {

                // Filters the array of transitions to get the transition which has the same origin
                // state and transition value.
                transition = Arrays.stream(transitions).filter(
                        t -> t.originState == actualState && t.transitionValue == actualCharacter)
                        .toArray(Transition[]::new)[0];

                sequence = sequence.substring(1);

            } catch (Exception e) {
                // Index out of bounds error means that the actual state doesn't have a transition
                // with the value of the actual character, therefore the sequence is rejected.
                return false;
            }
        }

        // A null transition at this part of the algorithm means that an empty sequence was passed.
        // Otherwise the last state will be the final state of the actual transition.
        String lastState = (transition != null) ? transition.finalState : startS;

        // If the last state matches with one of the values from the accept states then the sequence
        // is accepted, otherwise it is rejected.
        return Arrays.stream(acceptStates).anyMatch(s -> s.equals(lastState));
    }

    // Prints the characteristics of the DFA.
    public String toString() {
        String data = "";

        data += "[STATES] => " + Arrays.toString(states) + "\n";
        data += "[ALPHABET] => " + alphabet + "\n";
        data += "[q0] => " + startS + "\n";
        data += "[TRANSITIONS] => " + Arrays.toString(transitions) + "\n";
        data += "[ACCEPT STATES] => " + Arrays.toString(acceptStates) + "\n";

        return data;
    }

}
