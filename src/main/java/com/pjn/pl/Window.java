package com.pjn.pl;

import javax.swing.*;
public class Window extends JFrame {

    private static final long serialVersionUID = 1L;

    public Window() {
        super("PJN");

        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
