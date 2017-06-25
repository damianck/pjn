package com.pjn.pl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

/**
 * Created by miky on 20/06/2017.
 */
public class TFContainer extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SpringLayout layout;
    private JTextPane input;
    private JLabel inputLabel;
    private JButton analiza,wykresSlupkowy,chmuraSlow;
    private final String NAZWA_PLIKU = "tekst";

    public TFContainer() {
        layout = new SpringLayout();
        createContainer();
    }

    private void createContainer() {

        setLayout(layout);

        analiza = new JButton("Analizuj tekst");
        wykresSlupkowy = new JButton("Wyświetl wykres słupkowy");
        chmuraSlow = new JButton("Wyświetl chmurę słów");
        inputLabel = new JLabel("\\/~*~INPUT~*~\\/");

        input = new JTextPane();
        input.setSize(600, 400);

        //Layout Begin

        layout.putConstraint(SpringLayout.WEST, analiza, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, analiza, -10,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, analiza, -10,
                SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, wykresSlupkowy, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, wykresSlupkowy, -40,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, wykresSlupkowy, -10,
                SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, chmuraSlow, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, chmuraSlow, -70,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, chmuraSlow, -10,
                SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, inputLabel, 350,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, inputLabel, 10,
                SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, input, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, input, 50,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, input, -110,
                SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, input, -10,
                SpringLayout.EAST, this);

        //Layout End

        analiza.addActionListener(new Operacje(1));
        wykresSlupkowy.addActionListener(new Operacje(2));
        chmuraSlow.addActionListener(new Operacje(3));
        add(analiza);
        add(wykresSlupkowy);
        add(chmuraSlow);
        add(inputLabel);
        add(input);
    }

    private void zapiszTekstDoPliku(String tekst) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(NAZWA_PLIKU, true)));
            out.println(tekst);
            out.close();
        } catch (IOException e) {
            System.out.println("Blad Podczas zapisu pliku");
        }
    }

    private class Operacje implements ActionListener {

        private int nrAkcji;

        public Operacje(int nrAkcji) {
            this.nrAkcji = nrAkcji;
        }

        public void actionPerformed(ActionEvent arg0) {
            switch (nrAkcji) {
                case 1: {
                    // TF
                    Map<String, Integer> occurrences = new HashMap<String, Integer>();

                    String[] array = input.getText().split(" ");

                    for ( String word : array ) {
                        Integer oldCount = occurrences.get(word);
                        if ( oldCount == null ) {
                            oldCount = 0;
                        }
                        occurrences.put(word, oldCount + 1);
                    }

                    for(Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                        System.out.println(entry.getKey()+"/"+entry.getValue());
                    }


                    //TF-IDF
                    Map<String, Integer> oc = new HashMap<String, Integer>();

                    String[] array2 = input.getText().split(" ");

                    for ( String word : array2 ) {
                        Integer oldCount = oc.get(word);
                        if ( oldCount == null ) {
                            oldCount = 0;
                        }
                        oc.put(word, oldCount + 1);
                    }
                    for(Map.Entry<String, Integer> entry : oc.entrySet()) {
                        double wartosc = entry.getValue()*Math.log(4/2);
                        String slowo = entry.getKey();
                        System.out.println("slowo: "+slowo+"; "+"frekwencja: "+wartosc);
                    }
                };
                break;
                case 2:{
                    //TF
                    Map<String, Integer> occurrences = new HashMap<String, Integer>();

                    String[] array = input.getText().split(" ");

                    for ( String word : array ) {
                        Integer oldCount = occurrences.get(word);
                        if ( oldCount == null ) {
                            oldCount = 0;
                        }
                        occurrences.put(word, oldCount + 1);
                    }
                    ArrayList<Object[]> lista = new ArrayList<Object[]>();

                    for(Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                        Object[] tablica = {entry.getKey(),entry.getValue()};
                        lista.add(tablica);
                        //System.out.println(entry.getKey()+"/"+entry.getValue());
                    }

                    Chart ws = new Chart("Wykres slupkowy", "Wykres slupkowy",lista);
                    ws.rysujWykres();
                };
                break;
                case 3:{
                    final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
                    List<WordFrequency> wordFrequencies;
                    try {
                        zapiszTekstDoPliku(input.getText());
                        wordFrequencies = frequencyAnalyzer.load(NAZWA_PLIKU);
                        final Dimension dimension = new Dimension(800, 600);
                        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
                        wordCloud.setPadding(2);
                        wordCloud.setBackground(new CircleBackground(300));
                        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
                        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
                        wordCloud.build(wordFrequencies);
                        BufferedImage off_Image =
                                new BufferedImage(800, 600,
                                        BufferedImage.TYPE_INT_ARGB);
                        File outputfile = new File("saved.png");
                        ImageIO.write(off_Image, "png", outputfile);
                        wordCloud.writeToFile("saved.png");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                };
                break;

            }
        }
    }
}
