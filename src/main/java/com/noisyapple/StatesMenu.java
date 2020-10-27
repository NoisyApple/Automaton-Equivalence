package com.noisyapple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    public StatesMenu(DFA m) {

        this.m = m; // Automaton.
        states = new ArrayList<StateRow>();

        for (String s : m.getStates())
            states.add(new StateRow(s));

        mainPanel = new JPanel(new BorderLayout(10, 10));
        statesListPanel = new JPanel();

        statesListScroll = new JScrollPane();

        bottomPanel = new JPanel();
        btnAdd = new JButton("ADD");

        addAttributes();
        build();
        launch();

    }

    public void addAttributes() {
        setTitle("States menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        statesListPanel.setLayout(new BoxLayout(statesListPanel, BoxLayout.Y_AXIS));
        statesListScroll.setPreferredSize(new Dimension(380, 200));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void build() {

        for (StateRow sRow : states)
            statesListPanel.add(sRow);

        statesListScroll.getViewport().setView(statesListPanel);

        bottomPanel.add(btnAdd);

        mainPanel.add(statesListScroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void launch() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class StateRow extends JPanel {

        // Logic attributes.
        private String state;

        // Graphic attributes.
        private JLabel lblState;
        private JCheckBox cBxStartState, cBxAcceptState;
        private JButton btnDelete;

        public StateRow(String state) {

            lblState = new JLabel(state);
            cBxStartState = new JCheckBox("Start state.");
            cBxAcceptState = new JCheckBox("Accept state.");
            btnDelete = new JButton("DELETE");

            add(lblState);
            add(cBxStartState);
            add(cBxAcceptState);
            add(btnDelete);
        }

    }

}
