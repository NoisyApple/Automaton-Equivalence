package com.noisyapple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TransitionsMenu extends JFrame {

    // Logic attributes.
    private DFA m;

    // Graphic attributes.
    private ArrayList<TransitionRow> transitions;
    private JPanel mainPanel, bottomPanel, transitionsListPanel;
    private JScrollPane transitionsListScroll;
    private JButton btnAdd;

    // Main menu elements.
    private JButton btnHandler;
    private JLabel lblHandler;

    public TransitionsMenu(DFA m, JButton btnHandler, JLabel lblHandler) {

        this.m = m; // Automaton.
        this.btnHandler = btnHandler; // Handler button.
        this.lblHandler = lblHandler; // Handler label.

        mainPanel = new JPanel(new BorderLayout(10, 10));

        transitionsListScroll = new JScrollPane();

        bottomPanel = new JPanel();
        btnAdd = new JButton("ADD");

        btnHandler.setEnabled(false); // Handler is disabled while window is open.

        addListeners();
        addAttributes();
        updateTransitions();
        build();
        launch();
    }

    public void addListeners() {
        // ADD BUTTON CLICK EVENT +++
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Dropdown options.
                String[] stateOptions = m.getStates();
                String[] transValueOptions = m.getAlphabet().split("");

                // Origin state input.
                String originS = (String) JOptionPane.showInputDialog(null,
                        "Choose an origin state:", "New transition", JOptionPane.PLAIN_MESSAGE,
                        null, stateOptions, stateOptions[0]);

                // Transition value input.
                String transV = (String) JOptionPane.showInputDialog(null,
                        "Choose a transition value:", "New transition", JOptionPane.PLAIN_MESSAGE,
                        null, transValueOptions, transValueOptions[0]);

                // Final state input.
                String finalS = (String) JOptionPane.showInputDialog(null, "Choose a final state:",
                        "New transition", JOptionPane.PLAIN_MESSAGE, null, stateOptions,
                        stateOptions[0]);

                // New Transition object.
                Transition newTrans = new Transition(originS, transV.charAt(0), finalS);

                // Checks if transition already exists.
                boolean alreadyExists = Arrays.stream(m.getTransitions())
                        .anyMatch(t -> t.getOriginState() == newTrans.getOriginState()
                                && t.getTransitionValue() == newTrans.getTransitionValue()
                                && t.getFinalState() == newTrans.getFinalState());

                // Filters only valid new transitions.
                if (!alreadyExists) {
                    Transition[] actualTransitions = m.getTransitions();
                    Transition[] newTransitions = new Transition[actualTransitions.length + 1];

                    for (int i = 0; i < actualTransitions.length; i++)
                        newTransitions[i] = actualTransitions[i];

                    newTransitions[newTransitions.length - 1] = newTrans;

                    m.setTransitions(newTransitions);

                    updateTransitions();
                }

            }
        });
        // ADD BUTTON CLICK EVENT ---

        // WINDOW CLOSE EVENT +++
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                btnHandler.setEnabled(true); // Enables hanler when window is closed.

                if (m.getTransitions().length > 0)
                    lblHandler.setText(
                            "<html><span style='color: #5bb62d'>Transitions set.</span><html>");
            }
        });
        // WINDOW CLOSE EVENT ---
    }

    // Adds attributes to graphic elements.
    public void addAttributes() {
        setTitle("Transitions menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        transitionsListScroll.setPreferredSize(new Dimension(380, 200));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    // Builds the GUI.
    public void build() {
        bottomPanel.add(btnAdd);

        mainPanel.add(transitionsListScroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Launches the window.
    public void launch() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Loads all TransitionRow objects based on automaton data.
    public void updateTransitions() {
        transitions = new ArrayList<TransitionRow>();
        transitionsListPanel = new JPanel();

        transitionsListPanel.setLayout(new BoxLayout(transitionsListPanel, BoxLayout.Y_AXIS));

        // Takes all transitions from the automaton and adds them to the TransitionRow ArrayList.
        for (Transition t : m.getTransitions())
            transitions.add(new TransitionRow(t));

        // Sets background to each TransitionRow and adds them to the list.
        for (int i = 0; i < transitions.size(); i++) {
            transitions.get(i).setListBackground(i);
            transitionsListPanel.add(transitions.get(i));
        }

        transitionsListScroll.getViewport().setView(transitionsListPanel);
    }

    // Custom element to represent each transition of the automaton and its properties.
    class TransitionRow extends JPanel {

        // Logic attributes.
        private Transition transition;

        // Graphic attributes.
        private JLabel lblOriginState;
        private JLabel lblTransitionValue;
        private JLabel lblFinalState;
        private JButton btnDelete;

        public TransitionRow(Transition transition) {

            this.transition = transition;

            lblOriginState = new JLabel("<html><span style='font-size: 15px'>[ "
                    + transition.getOriginState() + " ] => </span><html>");
            lblTransitionValue = new JLabel("<html><span style='font-size: 15px'>' "
                    + transition.getTransitionValue() + " ' => </span><html>");
            lblFinalState = new JLabel("<html><span style='font-size: 15px'>[ "
                    + transition.getFinalState() + " ]</span><html>");
            btnDelete = new JButton("DELETE");

            addListeners();

            add(lblOriginState);
            add(lblTransitionValue);
            add(lblFinalState);
            add(btnDelete);
        }

        public void addListeners() {

            // DELETE BUTTON CLICK EVENT +++
            btnDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Transition[] newTransitions = (Arrays.stream(m.getTransitions())
                            .filter(t -> t != transition).toArray(Transition[]::new));

                    m.setTransitions(newTransitions);

                    updateTransitions();
                }
            });
            // DELETE BUTTON CLICK EVENT ---
        }

        // Background based on even and odd positions.
        public void setListBackground(int n) {
            Color c = Color.decode((n % 2 == 0) ? "#AAAAAA" : "#DDDDDD");
            setBackground(c);
        }

    }

}
