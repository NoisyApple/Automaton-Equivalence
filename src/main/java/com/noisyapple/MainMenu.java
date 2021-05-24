package com.noisyapple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

    // Logic attributes.
    private DFA m1, m2;

    // Graphic attributes.
    private JPanel mainPanel, alphabetPanel, m1Panel, m2Panel, m1StatesPanel, m2StatesPanel, m1TransitionsPanel,
            m2TransitionsPanel, m1SimplifyPanel, m2SimplifyPanel, bottomPanel;
    private JButton btnAlphabet, btnM1States, btnM2States, btnM1Transitions, btnM2Transitions, btnM1Simplify,
            btnM2Simplify, btnCompare, btnReset;
    private JLabel lblAlphabet, lblM1States, lblM2States, lblM1Transitions, lblM2Transitions;

    public MainMenu() {

        mainPanel = new JPanel(new BorderLayout(10, 10));

        alphabetPanel = new JPanel();
        btnAlphabet = new JButton("ALPHABET");
        lblAlphabet = new JLabel();

        m1Panel = new JPanel();
        m2Panel = new JPanel();

        m1StatesPanel = new JPanel();
        btnM1States = new JButton("STATES");
        lblM1States = new JLabel();

        m2StatesPanel = new JPanel();
        btnM2States = new JButton("STATES");
        lblM2States = new JLabel();

        m1TransitionsPanel = new JPanel();
        btnM1Transitions = new JButton("TRANSITIONS");
        lblM1Transitions = new JLabel();

        m2TransitionsPanel = new JPanel();
        btnM2Transitions = new JButton("TRANSITIONS");
        lblM2Transitions = new JLabel();

        m1SimplifyPanel = new JPanel();
        btnM1Simplify = new JButton("SIMPLIFY");

        m2SimplifyPanel = new JPanel();
        btnM2Simplify = new JButton("SIMPLIFY");

        bottomPanel = new JPanel();
        btnCompare = new JButton("COMPARE");
        btnReset = new JButton("RESET");

        defaultState();
        addListeners();
        addAttributes();
        build();
        launch();

    }

    public void defaultState() {

        m1 = new DFA();
        m2 = new DFA();

        String alphabet_1 = "ab"; // Sigma.

        String[] Q_1 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }; // Q.
        String q0__1 = "A"; // q0.
        Transition[] trans__1 = { new Transition("A", 'a', "B"), new Transition("A", 'b', "C"),
                new Transition("B", 'a', "D"), new Transition("B", 'b', "E"), new Transition("C", 'a', "A"),
                new Transition("C", 'b', "B"), new Transition("D", 'a', "C"), new Transition("D", 'b', "F"),
                new Transition("E", 'a', "G"), new Transition("E", 'b', "H"), new Transition("F", 'a', "H"),
                new Transition("F", 'b', "B"), new Transition("G", 'a', "I"), new Transition("G", 'b', "I"),
                new Transition("H", 'a', "J"), new Transition("H", 'b', "F"), new Transition("I", 'a', "H"),
                new Transition("I", 'b', "J"), new Transition("J", 'a', "G"), new Transition("J", 'b', "E"), }; // Delta.
        String[] F_1 = { "E", "G", "D" }; // F.

        m1 = new DFA(Q_1, alphabet_1, q0__1, trans__1, F_1); // (0+1)*10

        String alphabet_2 = "01"; // Sigma.

        String[] Q_2 = { "A", "B", "C", "D", "E", "F", "G", "H", "I" }; // Q.
        String q0__2 = "A"; // q0.
        Transition[] trans__2 = { new Transition("A", '0', "B"), new Transition("A", '1', "E"),
                new Transition("B", '0', "C"), new Transition("B", '1', "F"), new Transition("C", '0', "D"),
                new Transition("C", '1', "H"), new Transition("D", '0', "E"), new Transition("D", '1', "H"),
                new Transition("E", '0', "F"), new Transition("E", '1', "I"), new Transition("F", '0', "G"),
                new Transition("F", '1', "B"), new Transition("G", '0', "H"), new Transition("G", '1', "B"),
                new Transition("H", '0', "I"), new Transition("H", '1', "C"), new Transition("I", '0', "A"),
                new Transition("I", '1', "E"), }; // Delta.
        String[] F_2 = { "C", "F", "I" }; // F.

        m2 = new DFA(Q_2, alphabet_2, q0__2, trans__2, F_2); // (0+1)*10

        lblAlphabet.setText("<html><span style='color: #d02d3d'>No alphabet set.</span><html>");
        lblM1States.setText("<html><span style='color: #d02d3d'>No states set.</span><html>");
        lblM2States.setText("<html><span style='color: #d02d3d'>No states set.</span><html>");
        lblM1Transitions.setText("<html><span style='color: #d02d3d'>No transitions set.</span><html>");
        lblM2Transitions.setText("<html><span style='color: #d02d3d'>No transitions set.</span><html>");

        btnReset.setEnabled(false);
    }

    public void addListeners() {

        // ALPHABET BUTTON CLICK EVENT +++
        btnAlphabet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String commonAlphabet = JOptionPane.showInputDialog(null, "Enter an alphabet:", "New alphabet",
                        JOptionPane.PLAIN_MESSAGE);

                if (commonAlphabet != null) {
                    m1.setAlphabet(commonAlphabet);
                    m2.setAlphabet(commonAlphabet);

                    lblAlphabet.setText("<html><span style='color: #5bb62d'>Alphabet set.</span><html>");

                    btnReset.setEnabled(true);
                }

            }
        });
        // ALPHABET BUTTON CLICK EVENT ---

        // STATES BUTTON CLICK EVENT +++
        btnM1States.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatesMenu(m1, btnM1States, lblM1States, btnReset);
            }
        });

        btnM2States.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatesMenu(m2, btnM2States, lblM2States, btnReset);
            }
        });
        // STATES BUTTON CLICK EVENT ---

        // TRANSITION BUTTON CLICK EVENT +++
        btnM1Transitions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TransitionsMenu(m1, btnM1Transitions, lblM1Transitions);
            }
        });

        btnM2Transitions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TransitionsMenu(m2, btnM2Transitions, lblM2Transitions);
            }
        });
        // TRANSITION BUTTON CLICK EVENT ---

        // SIMPLIFY BUTTON CLICK EVENT +++
        btnM1Simplify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (m1.getStates().length > 0 && m1.getAlphabet().length() > 0 && m1.getStartS().length() > 0
                        && m1.getTransitions().length > 0 && m1.getAcceptStates().length > 0) {
                    DFA.simplify(m1);
                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: #18519A; font-size: 18px'>M1 Has been simplified! :)</span><html>",
                            "ADF Simplified!", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });

        btnM2Simplify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (m2.getStates().length > 0 && m2.getAlphabet().length() > 0 && m2.getStartS().length() > 0
                        && m2.getTransitions().length > 0 && m2.getAcceptStates().length > 0) {
                    DFA.simplify(m2);
                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: #18519A; font-size: 18px'>M1 Has been simplified! :)</span><html>",
                            "ADF Simplified!", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        // SIMPLIFY BUTTON CLICK EVENT ---

        // COMPARE BUTTON CLICK EVENT +++
        btnCompare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    new MooreTable(DFA.getMooreTable(m1, m2));
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: #18519A; font-size: 18px'>M1 Has been simplified! :)</span><html>",
                            "ADF Simplified!", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        // COMPARE BUTTON CLICK EVENT ---

        // RESET BUTTON CLICK EVENT +++
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                defaultState();
            }
        });
        // RESET BUTTON CLICK EVENT ---
    }

    public void addAttributes() {
        setTitle("Automaton equivalence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        alphabetPanel.setBorder(BorderFactory.createTitledBorder("Alphabet"));

        m1Panel.setBorder(BorderFactory.createTitledBorder("M1"));
        m2Panel.setBorder(BorderFactory.createTitledBorder("M2"));

        m1Panel.setLayout(new BoxLayout(m1Panel, BoxLayout.Y_AXIS));
        m2Panel.setLayout(new BoxLayout(m2Panel, BoxLayout.Y_AXIS));

        btnM1States.setPreferredSize(new Dimension(111, 26));
        lblM1States.setPreferredSize(new Dimension(103, 26));
        btnM2States.setPreferredSize(new Dimension(111, 26));
        lblM2States.setPreferredSize(new Dimension(103, 26));

        btnM1Transitions.setPreferredSize(new Dimension(111, 26));
        lblM1Transitions.setPreferredSize(new Dimension(103, 26));
        btnM2Transitions.setPreferredSize(new Dimension(111, 26));
        lblM2Transitions.setPreferredSize(new Dimension(103, 26));

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    }

    public void build() {
        alphabetPanel.add(btnAlphabet);
        alphabetPanel.add(lblAlphabet);

        m1StatesPanel.add(btnM1States);
        m1StatesPanel.add(lblM1States);

        m2StatesPanel.add(btnM2States);
        m2StatesPanel.add(lblM2States);

        m1TransitionsPanel.add(btnM1Transitions);
        m1TransitionsPanel.add(lblM1Transitions);

        m2TransitionsPanel.add(btnM2Transitions);
        m2TransitionsPanel.add(lblM2Transitions);

        m1SimplifyPanel.add(btnM1Simplify);

        m2SimplifyPanel.add(btnM2Simplify);

        bottomPanel.add(btnCompare);
        bottomPanel.add(btnReset);

        m1Panel.add(m1StatesPanel);
        m2Panel.add(m2StatesPanel);

        m1Panel.add(m1TransitionsPanel);
        m2Panel.add(m2TransitionsPanel);

        m1Panel.add(m1SimplifyPanel);
        m2Panel.add(m2SimplifyPanel);

        mainPanel.add(alphabetPanel, BorderLayout.NORTH);
        mainPanel.add(m1Panel, BorderLayout.WEST);
        mainPanel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.CENTER);
        mainPanel.add(m2Panel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void launch() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
