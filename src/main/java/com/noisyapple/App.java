package com.noisyapple;

// import java.util.ArrayList;
// import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        // new MainMenu();

        // String[] cases = {"10", "010110", "", "01"};

        // String alphabet = "01"; // Sigma.

        // String[] Q1 = {"i", "0", "1", "00", "01", "10", "11"}; // Q.
        // String q0_1 = "i"; // q0.
        // Transition[] trans_1 = {new Transition("i", '0', "0"), new Transition("i",
        // '1', "1"),
        // new Transition("0", '0', "00"), new Transition("0", '1', "01"),
        // new Transition("1", '0', "10"), new Transition("1", '1', "11"),
        // new Transition("00", '0', "00"), new Transition("00", '1', "01"),
        // new Transition("01", '0', "10"), new Transition("01", '1', "11"),
        // new Transition("10", '0', "00"), new Transition("10", '1', "01"),
        // new Transition("11", '0', "10"), new Transition("11", '1', "11")}; // Delta.
        // String[] F1 = {"10"}; // F.

        // DFA M1 = new DFA(Q1, alphabet, q0_1, trans_1, F1); // (0+1)*10

        // new StatesMenu(M1);

        // String[] Q2 = {"A", "B", "C"}; // Q.
        // String q0_2 = "A"; // q0.
        // Transition[] trans_2 = {new Transition("A", '0', "A"), new Transition("A",
        // '1', "B"),
        // new Transition("B", '1', "B"), new Transition("B", '0', "C"),
        // new Transition("C", '1', "B"), new Transition("C", '0', "A"),}; // Delta.
        // String[] F2 = {"C"}; // F.

        // DFA M2 = new DFA(Q2, alphabet, q0_2, trans_2, F2); // (0+1)*10

        // System.out.println(M1.toString());
        // System.out.println(M2.toString());

        // for (String c : cases) {
        // System.out.println("[TEST SEQUENCE] => \"" + c + "\"");
        // System.out.println("M1 => " + M1.evaluate(c));
        // System.out.println("M2 => " + M2.evaluate(c) + "\n");
        // }

        // Stack<StateTuple[]> data = DFA.areEquivalent(M1, M2);

        // for (StateTuple[] row : data) {
        // System.out.println(Arrays.toString(row));
        // }

        ////////////////////////////////////

        // System.out.println("\n\n");

        // String alphabet2 = "ab"; // Sigma.

        // String[] Q_1 = { "q0", "q1", "q2" }; // Q.
        // String q0__1 = "q0"; // q0.
        // Transition[] trans__1 = { new Transition("q0", 'a', "q2"), new
        // Transition("q0", 'b', "q1"),
        // new Transition("q1", 'a', "q1"), new Transition("q1", 'b', "q1"), new
        // Transition("q2", 'a', "q0"),
        // new Transition("q2", 'b', "q1") }; // Delta.
        // String[] F_1 = { "q0", "q2" }; // F.

        // DFA M_1 = new DFA(Q_1, alphabet2, q0__1, trans__1, F_1); // (0+1)*10

        // String[] Q_2 = { "r0", "r1" }; // Q.
        // String q0__2 = "r0"; // q0.
        // Transition[] trans__2 = { new Transition("r0", 'a', "r0"), new
        // Transition("r0", 'b', "r1"),
        // new Transition("r1", 'a', "r1"), new Transition("r1", 'b', "r1") }; // Delta.
        // String[] F_2 = { "r0" }; // F.

        // DFA M_2 = new DFA(Q_2, alphabet2, q0__2, trans__2, F_2); // (0+1)*10

        // System.out.println(M_1.toString());
        // System.out.println(M_2.toString());

        // ArrayList<StateTuple[]> data2 = DFA.getMooreTable(M_1, M_2);

        // for (StateTuple[] row : data2) {
        // System.out.println(Arrays.toString(row));
        // }

        // System.out.println(DFA.areEquivalent(M_1, M_2));

        // String alphabet = "ab"; // Sigma.

        // String[] Q_1 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }; // Q.
        // String q0__1 = "A"; // q0.
        // Transition[] trans__1 = { new Transition("A", 'a', "B"), new Transition("A",
        // 'b', "C"),
        // new Transition("B", 'a', "D"), new Transition("B", 'b', "E"), new
        // Transition("C", 'a', "A"),
        // new Transition("C", 'b', "B"), new Transition("D", 'a', "C"), new
        // Transition("D", 'b', "F"),
        // new Transition("E", 'a', "G"), new Transition("E", 'b', "H"), new
        // Transition("F", 'a', "H"),
        // new Transition("F", 'b', "B"), new Transition("G", 'a', "I"), new
        // Transition("G", 'b', "I"),
        // new Transition("H", 'a', "J"), new Transition("H", 'b', "F"), new
        // Transition("I", 'a', "H"),
        // new Transition("I", 'b', "J"), new Transition("J", 'a', "G"), new
        // Transition("J", 'b', "E"), }; // Delta.
        // String[] F_1 = { "E", "G", "D" }; // F.

        // DFA M_1 = new DFA(Q_1, alphabet, q0__1, trans__1, F_1); // (0+1)*10

        // DFA.simplify(M_1);

        // System.out.println(M_1.toString());

        // String alphabet = "01"; // Sigma.

        // String[] Q_1 = { "A", "B", "C", "D", "E", "F", "G", "H", "I" }; // Q.
        // String q0__1 = "A"; // q0.
        // Transition[] trans__1 = { new Transition("A", '0', "B"), new Transition("A",
        // '1', "E"),
        // new Transition("B", '0', "C"), new Transition("B", '1', "F"), new
        // Transition("C", '0', "D"),
        // new Transition("C", '1', "H"), new Transition("D", '0', "E"), new
        // Transition("D", '1', "H"),
        // new Transition("E", '0', "F"), new Transition("E", '1', "I"), new
        // Transition("F", '0', "G"),
        // new Transition("F", '1', "B"), new Transition("G", '0', "H"), new
        // Transition("G", '1', "B"),
        // new Transition("H", '0', "I"), new Transition("H", '1', "C"), new
        // Transition("I", '0', "A"),
        // new Transition("I", '1', "E"), }; // Delta.
        // String[] F_1 = { "C", "F", "I" }; // F.

        // DFA M_1 = new DFA(Q_1, alphabet, q0__1, trans__1, F_1); // (0+1)*10

        // DFA.simplify(M_1);

        // System.out.println(M_1.toString());

    }

}
