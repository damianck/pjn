package com.pjn.pl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.util.ArrayList;
import java.util.Locale;

import morfologik.stemming.WordData;

public class MainContainer extends JPanel {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SpringLayout layout;
    private JTextPane input;
    private AbstractDocument dokument;
    private JTextArea output;
    private JLabel inputLabel, outputLabel;

    public MainContainer() {
        layout = new SpringLayout();
        createContainer();
    }

    private void createContainer() {

        setLayout(layout);

        inputLabel = new JLabel("Insert text here");
        outputLabel = new JLabel("Results output");

        input = new JTextPane();
        input.setSize(200, 400);
        StyledDocument stylDok = input.getStyledDocument();
        dokument = (AbstractDocument) stylDok;

        output = new JTextArea();
        output.setEditable(false);

        //Layout Begin

        layout.putConstraint(SpringLayout.WEST, inputLabel, 180,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, inputLabel, 25,
                SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, outputLabel, -80,
                SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, outputLabel, 25,
                SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, input, 5,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, input, 50,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, input, -10,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, input, -320,
                SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.EAST, output, -5,
                SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, output, 50,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, output, -10,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, output, 520,
                SpringLayout.WEST, this);


        //Layout End

        dokument.addDocumentListener(new ListenerDokumentu());

        add(inputLabel);
        add(outputLabel);
        add(input);
        add(output);
    }

    protected class ListenerDokumentu implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        public void removeUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        public void changedUpdate(DocumentEvent e) {
            displayEditInfo(e);
        }

        private void displayEditInfo(DocumentEvent e) {
            Document dok = e.getDocument();
            PolishStemmer stemmer = new PolishStemmer();
            try {
                output.setText("");
                String[] tablica = dok.getText(0, dok.getLength()).toLowerCase(new Locale("pl")).split("[\\s\\.\\,]+");
                ArrayList<String> lista = new ArrayList<String>();
                for (String t : dok.getText(0, dok.getLength()).toLowerCase(new Locale("pl")).split("[\\s\\.\\,]+")) {
                    lista.add("> '" + t + "'");

                    for (WordData wd : stemmer.lookup(t)) {
                        lista.add(" - " + (wd.getStem() == null ? "<null>" : wd.getStem()));
                    }
                    lista.add("\n");
                }
                for (int i = 0; i < lista.size(); i++) {
                    output.append(lista.get(i));
                }
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }

}
