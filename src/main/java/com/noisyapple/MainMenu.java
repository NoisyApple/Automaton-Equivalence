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
    private String commonAlphabet;

    // Graphic attributes.
    private JPanel mainPanel, alphabetPanel, m1Panel, m2Panel, m1StatesPanel, m2StatesPanel,
            m1TransitionsPanel, m2TransitionsPanel, bottomPanel;
    private JButton btnAlphabet, btnM1States, btnM2States, btnM1Transitions, btnM2Transitions,
            btnCompare, btnReset;
    private JLabel lblAlphabet, lblM1States, lblM2States, lblM1Transitions, lblM2Transitions;

    public MainMenu() {

        m1 = new DFA();
        m2 = new DFA();

        mainPanel = new JPanel(new BorderLayout(10, 10));

        alphabetPanel = new JPanel();
        btnAlphabet = new JButton("ALPHABET");
        lblAlphabet = new JLabel("No alphabet set.");

        m1Panel = new JPanel();
        m2Panel = new JPanel();

        m1StatesPanel = new JPanel();
        btnM1States = new JButton("STATES");
        lblM1States = new JLabel("No states set.");

        m2StatesPanel = new JPanel();
        btnM2States = new JButton("STATES");
        lblM2States = new JLabel("No states set.");

        m1TransitionsPanel = new JPanel();
        btnM1Transitions = new JButton("TRANSITIONS");
        lblM1Transitions = new JLabel("No transitions set.");

        m2TransitionsPanel = new JPanel();
        btnM2Transitions = new JButton("TRANSITIONS");
        lblM2Transitions = new JLabel("No transitions set.");

        bottomPanel = new JPanel();
        btnCompare = new JButton("COMPARE");
        btnReset = new JButton("RESET");

        addListeners();
        addAttributes();
        build();
        launch();

    }

    public void addListeners() {

        // ALPHABET BUTTON CLICK EVENT +++
        btnAlphabet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commonAlphabet = JOptionPane.showInputDialog(null, "Enter an alphabet:");

                m1.setAlphabet(commonAlphabet);
                m2.setAlphabet(commonAlphabet);
            }
        });
        // ALPHABET BUTTON CLICK EVENT ---

        // STATES BUTTON CLICK EVENT +++
        btnM1States.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatesMenu(m1, btnM1States);
            }
        });

        btnM2States.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StatesMenu(m2, btnM2States);
            }
        });
        // STATES BUTTON CLICK EVENT ---
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

        bottomPanel.add(btnCompare);
        bottomPanel.add(btnReset);

        m1Panel.add(m1StatesPanel);
        m2Panel.add(m2StatesPanel);

        m1Panel.add(m1TransitionsPanel);
        m2Panel.add(m2TransitionsPanel);

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
