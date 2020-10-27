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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class StatesMenu extends JFrame {

    // Logic attributes.
    private DFA m;

    // Graphic attributes.
    private ArrayList<StateRow> states;
    private JPanel mainPanel, bottomPanel, statesListPanel;
    private JScrollPane statesListScroll;
    private JButton btnAdd;

    // Main menu elements.
    private JButton btnHandler, btnReset;
    private JLabel lblHandler;

    public StatesMenu(DFA m, JButton btnHandler, JLabel lblHandler, JButton btnReset) {

        this.m = m; // Automaton.
        this.btnHandler = btnHandler; // Handler button.
        this.lblHandler = lblHandler; // Handler label.
        this.btnReset = btnReset; // Reset button.

        mainPanel = new JPanel(new BorderLayout(10, 10));

        statesListScroll = new JScrollPane();

        bottomPanel = new JPanel();
        btnAdd = new JButton("ADD");

        btnHandler.setEnabled(false); // Handler is disabled while window is open.

        addListeners();
        addAttributes();
        updateStates();
        build();
        launch();
    }

    public void addListeners() {
        // ADD BUTTON CLICK EVENT +++
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // New state from input.
                String newS = JOptionPane.showInputDialog(null, "Enter the new state:", "New state",
                        JOptionPane.PLAIN_MESSAGE);

                // Checks if state already exists.
                boolean alreadyExists = Arrays.stream(m.getStates()).anyMatch(s -> s.equals(newS));

                // Filters only valid new states.
                if (newS != null && newS.length() > 0 && !alreadyExists) {
                    String[] actualStates = m.getStates();
                    String[] newStates = new String[actualStates.length + 1];

                    for (int i = 0; i < actualStates.length; i++)
                        newStates[i] = actualStates[i];

                    newStates[newStates.length - 1] = newS;

                    m.setStates(newStates);

                    updateStates();
                }

            }
        });
        // ADD BUTTON CLICK EVENT ---

        // WINDOW CLOSE EVENT +++
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                btnHandler.setEnabled(true); // Enables hanler when window is closed.

                if (m.getStates().length > 0) {
                    lblHandler
                            .setText("<html><span style='color: #5bb62d'>States set.</span><html>");
                    btnReset.setEnabled(true);
                }
            }
        });
        // WINDOW CLOSE EVENT ---
    }

    // Adds attributes to graphic elements.
    public void addAttributes() {
        setTitle("States menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        statesListScroll.setPreferredSize(new Dimension(380, 200));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    // Builds the GUI.
    public void build() {
        bottomPanel.add(btnAdd);

        mainPanel.add(statesListScroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Launches the window.
    public void launch() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Loads all StateRow objects based on automaton data.
    public void updateStates() {
        states = new ArrayList<StateRow>();
        statesListPanel = new JPanel();

        statesListPanel.setLayout(new BoxLayout(statesListPanel, BoxLayout.Y_AXIS));

        // Takes all states from the automaton and adds them to the StateRow ArrayList.
        for (String s : m.getStates())
            states.add(new StateRow(s));

        // Sets background to each StateRow and adds them to the list.
        for (int i = 0; i < states.size(); i++) {
            states.get(i).setListBackground(i);
            statesListPanel.add(states.get(i));
        }

        statesListScroll.getViewport().setView(statesListPanel);
    }

    // Custom element to represent each state of the automaton and its properties.
    class StateRow extends JPanel {

        // Logic attributes.
        private String state;

        // Graphic attributes.
        private JLabel lblState;
        private JCheckBox cBxStartState, cBxAcceptState;
        private JButton btnDelete;

        public StateRow(String state) {

            this.state = state;

            lblState = new JLabel(
                    "<html><span style='font-size: 15px'>[ " + state + " ]</span><html>");
            cBxStartState = new JCheckBox("Start state.");
            cBxAcceptState = new JCheckBox("Accept state.");
            btnDelete = new JButton("DELETE");

            addListeners();
            configureElements();

            add(lblState);
            add(cBxStartState);
            add(cBxAcceptState);
            add(btnDelete);
        }

        public void addListeners() {

            // DELETE BUTTON CLICK EVENT +++
            btnDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String[] newStates = (Arrays.stream(m.getStates()).filter(s -> !s.equals(state))
                            .toArray(String[]::new));
                    String[] newAcceptStates = (Arrays.stream(m.getAcceptStates())
                            .filter(s -> !s.equals(state)).toArray(String[]::new));

                    m.setStates(newStates);
                    m.setAcceptStates(newAcceptStates);

                    if (m.getStartS().equals(state))
                        m.setStartS("");

                    updateStates();
                }
            });
            // DELETE BUTTON CLICK EVENT ---

            // START STATE CHECKBOX CHECK EVENT +++
            cBxStartState.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (cBxStartState.isSelected()) {
                        m.setStartS(state);
                    }

                    updateStates();
                }
            });
            // START STATE CHECKBOX CHECK EVENT +++

            // ACCEPT STATE CHECKBOX CHECK EVENT +++
            cBxAcceptState.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String[] actualAcceptStates = m.getAcceptStates();
                    String[] newAcceptStates;

                    if (cBxAcceptState.isSelected()) { // Add accept state.

                        newAcceptStates = new String[actualAcceptStates.length + 1];

                        for (int i = 0; i < actualAcceptStates.length; i++)
                            newAcceptStates[i] = actualAcceptStates[i];

                        newAcceptStates[newAcceptStates.length - 1] = state;

                    } else { // Remove accept state.
                        newAcceptStates = (Arrays.stream(actualAcceptStates)
                                .filter(s -> !s.equals(state)).toArray(String[]::new));
                    }

                    m.setAcceptStates(newAcceptStates);
                    updateStates();
                }
            });
            // ACCEPT STATE CHECKBOX CHECK EVENT +++
        }

        // Set the state of GUI elements based on the state.
        public void configureElements() {
            if (m.getStartS() == state) {
                cBxStartState.setSelected(true);
            }

            if (Arrays.stream(m.getAcceptStates()).anyMatch(s -> s.equals(state))) {
                cBxAcceptState.setSelected(true);
            }
        }

        // Background based on even and odd positions.
        public void setListBackground(int n) {
            Color c = Color.decode((n % 2 == 0) ? "#AAAAAA" : "#DDDDDD");
            cBxAcceptState.setBackground(c);
            cBxStartState.setBackground(c);
            setBackground(c);
        }

    }

}
