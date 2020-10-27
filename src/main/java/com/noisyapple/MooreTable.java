package com.noisyapple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MooreTable {

    // Logic attributes.
    ArrayList<StateTuple[]> mooreTable;

    // Graphic attributes.
    private JFrame frame;
    private JPanel mainPanel, middlePanel, topPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane tableScroll;
    private JLabel lblTop;

    public MooreTable(ArrayList<StateTuple[]> mooreTable) {

        this.mooreTable = mooreTable;

        frame = new JFrame("Moore Table");
        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel();
        middlePanel = new JPanel();

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableScroll = new JScrollPane();

        lblTop = new JLabel(
                "<html><span style='color: #18519A; font-size: 18px'>M1 AND M2 ARE EQUIVALENT! :)</span><html>");

        addAttributes();
        buildTable();
        build();
        launch();
    }

    // Builds table data.
    public void buildTable() {

        // Sets the first row from the data as the table headers.
        StateTuple[] tupleHeaders = mooreTable.get(0);
        String[] headers = new String[tupleHeaders.length];

        for (int i = 0; i < tupleHeaders.length; i++) {
            StateTuple t = tupleHeaders[i];

            if (i == 0)
                headers[i] = t.toString();
            else
                headers[i] = t.getLeftState();
        }

        tableModel.setColumnIdentifiers(headers);

        // Fills table with the other rows.
        for (int i = 1; i < mooreTable.size(); i++) {
            String[] row =
                    Arrays.stream(mooreTable.get(i)).map(e -> e.toString()).toArray(String[]::new);
            tableModel.addRow(row);
        }
    }

    // Adds attributes to graphic elements.
    private void addAttributes() {

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
        table.setRowHeight(24);
        tableScroll.setPreferredSize(new Dimension(600, 200));

        // This method is used to set customed format to the table.
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                // Changes color based on even and odd rows.
                if (row % 2 == 0) {
                    setBackground(Color.decode("#B0CCFD"));
                } else {
                    setBackground(Color.decode("#DDE9FE"));
                }

                // Format for each cell.
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("Dialog", Font.PLAIN, 18));

                return this;
            }
        });
    }

    // Builds the GUI.
    private void build() {
        tableScroll.getViewport().setView(table);
        middlePanel.add(tableScroll);

        topPanel.add(lblTop);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        frame.add(mainPanel);
    }

    // Launches the window.
    private void launch() {
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

}
