package com.pjn.pl;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

public class AnalysisResults extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea output;

    public AnalysisResults(String applicationTitle, String date) {
        super(applicationTitle);
        setSize(600, 150);
        System.out.println(date);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(new java.awt.Dimension(600, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        output = new JTextArea();
        output.setEditable(false);
        output.setText(date);
        panel.add(output);
        setContentPane(panel);
    }
}
