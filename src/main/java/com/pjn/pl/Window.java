package com.pjn.pl;

import javax.swing.*;

public class Window extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Window() {
        super("PJN");

        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Identyfikacja TF | TF-IDF", new TFContainer());
        jtp.addTab("Identyfikacja LSA | RAKE", new LSAContainer());
        add(jtp);
        setSize(WIDTH, HEIGHT);
    }
}
