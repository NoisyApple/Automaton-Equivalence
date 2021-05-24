package com.noisyapple;

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;

public class AutomatonCanvas extends JPanel {

    private final int W = 100;
    private final int H = 100;

    private DFA automaton;

    public AutomatonCanvas(DFA automaton) {
        this.automaton = automaton;

        setAttributes();
    }

    private void setAttributes() {
        this.setVisible(true);
        this.setSize(100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.decode("#333333"));
        g2.fillRect(0, 0, W, H);
    }

    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

}
