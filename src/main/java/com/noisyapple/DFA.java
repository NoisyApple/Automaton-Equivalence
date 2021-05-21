package com.noisyapple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFA {

    public static int NON_ACCEPT_STATE = 0;
    public static int ACCEPT_STATE = 1;
    public static int NOT_FOUND_IN_DFA = 2;

    private String[] states; // Q.
    private String alphabet; // Sigma.
    private String startS; // q0.
    private Transition[] transitions; // Delta.
    private String[] acceptStates; // F.

    public DFA(String[] states, String alphabet, String startS, Transition[] transitions, String[] acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.startS = startS;
        this.transitions = transitions;
        this.acceptStates = acceptStates;
    }

    public DFA() {
        this.states = new String[] {};
        this.alphabet = "";
        this.startS = "";
        this.transitions = new Transition[] {};
        this.acceptStates = new String[] {};
    }

    // Evaluates a sequence with the automaton.
    public boolean evaluate(String sequence) {

        Transition transition = null;

        // Iterates through each character from the sequence.
        while (sequence.length() > 0) {

            // Sets the actual state based on the transition's finalState or the start
            // state.
            String actualState = (transition != null) ? transition.getFinalState() : startS;
            char actualCharacter = sequence.charAt(0);

            // Try catch block prevents array index out of bounds error.
            try {

                // Filters the array of transitions to get the transition which has the same
                // origin
                // state and transition value.
                transition = Arrays.stream(transitions)
                        .filter(t -> t.getOriginState() == actualState && t.getTransitionValue() == actualCharacter)
                        .toArray(Transition[]::new)[0];

                sequence = sequence.substring(1);

            } catch (Exception e) {
                // Index out of bounds error means that the actual state doesn't have a
                // transition
                // with the value of the actual character, therefore the sequence is rejected.
                return false;
            }
        }

        // A null transition at this part of the algorithm means that an empty sequence
        // was passed.
        // Otherwise the last state will be the final state of the actual transition.
        String lastState = (transition != null) ? transition.getFinalState() : startS;

        // If the last state matches with one of the values from the accept states then
        // the sequence
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

    public static ArrayList<StateTuple[]> areEquivalent(DFA m1, DFA m2) {

        ArrayList<StateTuple[]> mooreTable = new ArrayList<StateTuple[]>(); // Visualizable data.
        Stack<StateTuple> closedSet = new Stack<StateTuple>(); // Valid tuples.
        ArrayList<StateTuple> openSet = new ArrayList<StateTuple>(); // Tuples to be evaluated.

        String alphabet = m1.alphabet; // Common alphabet.

        // Both alphabets must be the same.
        if (!m1.alphabet.equals(m2.alphabet))
            return mooreTable;

        // Table header is added.
        StateTuple[] tableHeader = new StateTuple[alphabet.length() + 1];
        tableHeader[0] = new StateTuple("q", "q'");

        for (int i = 0; i < alphabet.length(); i++)
            tableHeader[i + 1] = new StateTuple(String.valueOf(alphabet.charAt(i)), "");

        mooreTable.add(tableHeader);

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
                String m1TransState = getTransitionResultState(m1State, alphabet.charAt(index), m1);

                String m2State = lastTuple.getRightState(); // State from m2.

                // Transition state from origin state (m2State) with actual alphabet symbol.
                String m2TransState = getTransitionResultState(m2State, alphabet.charAt(index), m2);

                // New tuple with found transition states.
                StateTuple newTuple = new StateTuple(m1TransState, m2TransState);

                // Resultant state type from transition on m1.
                int m1TransStateType = getStateType(m1TransState, m1);

                // Resultant state type from transition on m2.
                int m2TransStateType = getStateType(m2TransState, m2);

                // Checks whether both transition states are accept states or not.
                if ((m1TransStateType != NOT_FOUND_IN_DFA && m2TransStateType != NOT_FOUND_IN_DFA)
                        && (m1TransStateType == m2TransStateType)) {

                    boolean evaluatedTuple = false;

                    // Checks if the new tuple has already been evaluated.
                    for (StateTuple tuple : closedSet)
                        evaluatedTuple = evaluatedTuple || (tuple.getLeftState() == newTuple.getLeftState()
                                && tuple.getRightState() == newTuple.getRightState());

                    // Or if its already in the openSet (to be evaluated).
                    for (StateTuple tuple : openSet)
                        evaluatedTuple = evaluatedTuple || (tuple.getLeftState() == newTuple.getLeftState()
                                && tuple.getRightState() == newTuple.getRightState());

                    // Adds tuple to the table row.
                    tableRow[i + 1] = newTuple;

                    // Only adds un-evaluated tuples to the openSet "Queue" and closedSet.
                    if (!evaluatedTuple) {
                        closedSet.push(newTuple);
                        openSet.add(newTuple);
                    }

                } else {
                    return new ArrayList<StateTuple[]>(); // Empty table (not equivalent).
                }

            }

            mooreTable.add(tableRow); // Adds row to mooreTable.
        }

        return mooreTable;
    }

    // public static DFA simplify(DFA originalDFA){

    // }

    // Returns the type of a given state based on a given DFA.
    public static int getStateType(String state, DFA containerDFA) {

        boolean doesDFAContainsGivenState = Arrays.stream(containerDFA.states).anyMatch(s -> s.equals(state));

        if (doesDFAContainsGivenState) {
            boolean isAcceptState = Arrays.stream(containerDFA.acceptStates).anyMatch(s -> s.equals(state));
            return isAcceptState ? ACCEPT_STATE : NON_ACCEPT_STATE;
        } else {
            return NOT_FOUND_IN_DFA;
        }

    }

    // Returns the result state for the given transition data.
    public static String getTransitionResultState(String originState, char symbol, DFA containerDFA) {

        String resultState = "";

        try {
            resultState = Arrays.stream(containerDFA.transitions)
                    .filter(t -> t.getOriginState() == originState && t.getTransitionValue() == symbol)
                    .toArray(Transition[]::new)[0].getFinalState();
        } catch (Exception e) {
            resultState = "null";
        }

        return resultState;

    }

    // GETTERS +++
    public String[] getStates() {
        return states;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public String getStartS() {
        return startS;
    }

    public Transition[] getTransitions() {
        return transitions;
    }

    public String[] getAcceptStates() {
        return acceptStates;
    }
    // GETTERS ---

    // SETTERS +++
    public void setStates(String[] states) {
        this.states = states;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }

    public void setAcceptStates(String[] acceptStates) {
        this.acceptStates = acceptStates;
    }
    // SETTERS ---
}
