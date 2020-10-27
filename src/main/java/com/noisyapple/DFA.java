package com.noisyapple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFA {

    private String[] states; // Q.
    private String alphabet; // Sigma.
    private String startS; // q0.
    private Transition[] transitions; // Delta.
    private String[] acceptStates; // F.

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

    public static Stack<StateTuple[]> areEquivalent(DFA m1, DFA m2) {

        Stack<StateTuple[]> dataStack = new Stack<StateTuple[]>(); // Visualizable data.
        Stack<StateTuple> closedSet = new Stack<StateTuple>(); // Valid tuples.
        ArrayList<StateTuple> openSet = new ArrayList<StateTuple>(); // Tuples to be evaluated.

        String alphabet = m1.alphabet; // Common alphabet.

        // Both alphabets must be the same.
        if (!m1.alphabet.equals(m2.alphabet))
            return dataStack;

        // Table header is added.
        StateTuple[] tableHeader = new StateTuple[alphabet.length() + 1];
        tableHeader[0] = new StateTuple("q", "q'");

        for (int i = 0; i < alphabet.length(); i++)
            tableHeader[i + 1] = new StateTuple(String.valueOf(alphabet.charAt(i)), "");

        dataStack.push(tableHeader);

        // First tuple (initial states) is added to both sets.
        closedSet.push(new StateTuple(m1.startS, m2.startS));
        openSet.add(new StateTuple(m1.startS, m2.startS));

        while (openSet.size() > 0) {

            StateTuple[] tableRow = new StateTuple[alphabet.length() + 1]; // Row.
            StateTuple lastTuple = openSet.remove(0); // Last tuple is popped from the "Queue".

            tableRow[0] = lastTuple;

            // Iterates through each symbol from the alphabet.
            for (int i = 0; i < alphabet.length(); i++) {
                int index = i; // To prevent error at lambda expression.

                String m1State = lastTuple.getLeftState(); // State from m1.

                // Transition state from origin state (m1State) with actual alphabet symbol.
                String m1TransState = Arrays.stream(m1.transitions)
                        .filter(t -> t.originState == m1State
                                && t.transitionValue == alphabet.charAt(index))
                        .toArray(Transition[]::new)[0].finalState;

                String m2State = lastTuple.getRightState(); // State from m2.

                // Transition state from origin state (m2State) with actual alphabet symbol.
                String m2TransState = Arrays.stream(m2.transitions)
                        .filter(t -> t.originState == m2State
                                && t.transitionValue == alphabet.charAt(index))
                        .toArray(Transition[]::new)[0].finalState;

                // New tuple with found transition states.
                StateTuple newTuple = new StateTuple(m1TransState, m2TransState);

                // Checks if transition state of m1's origin state is within m1's accept state set.
                boolean m1TransSIsAcceptState =
                        Arrays.stream(m1.acceptStates).anyMatch(s -> s.equals(m1TransState));

                // Checks if transition state of m2's origin state is within m2's accept state set.
                boolean m2TransSIsAcceptState =
                        Arrays.stream(m2.acceptStates).anyMatch(s -> s.equals(m2TransState));

                // Checks whether both transition states are accept states or not.
                if ((m1TransSIsAcceptState && m2TransSIsAcceptState)
                        || (!m1TransSIsAcceptState && !m2TransSIsAcceptState)) {

                    boolean evaluatedTuple = false;

                    // Checks if the new tuple has already been evaluated.
                    for (StateTuple tuple : closedSet)
                        evaluatedTuple =
                                evaluatedTuple || (tuple.getLeftState() == newTuple.getLeftState()
                                        && tuple.getRightState() == newTuple.getRightState());

                    // Or if its already in the openSet (to be evaluated).
                    for (StateTuple tuple : openSet)
                        evaluatedTuple =
                                evaluatedTuple || (tuple.getLeftState() == newTuple.getLeftState()
                                        && tuple.getRightState() == newTuple.getRightState());

                    // Adds tuple to the table row.
                    tableRow[i + 1] = newTuple;

                    // Only adds un-evaluated tuples to the openSet "Queue" and closedSet.
                    if (!evaluatedTuple) {
                        closedSet.push(newTuple);
                        openSet.add(newTuple);
                    }

                } else {
                    return new Stack<StateTuple[]>(); // Empty table (not equivalent).
                }

            }

            dataStack.push(tableRow); // Adds row to dataStack.
        }

        return dataStack;
    }

    public String[] getStates() {
        return states;
    }

}
