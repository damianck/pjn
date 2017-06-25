package com.pjn.pl;

import tml.corpus.CorpusParameters;
import tml.corpus.SearchResultsCorpus;
import tml.storage.Repository;
import tml.vectorspace.TermWeighting;
import tml.vectorspace.operations.PassagesSimilarity;
import tml.vectorspace.operations.RapidAutomaticKeywordExtraction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by miky on 20/06/2017.
 */
public class LSAContainer extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SpringLayout layout;
    private JButton dodajTekst,analizujTekst;

    public LSAContainer() {
        layout = new SpringLayout();
        createContainer();
    }

    private void createContainer() {

        setLayout(layout);

        analizujTekst = new JButton("Analizuj teksty");
        dodajTekst = new JButton("Dodaj teksty do bazy danych z folderu repoSource");

        //Layout Begin

        layout.putConstraint(SpringLayout.WEST, analizujTekst, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, analizujTekst, 10,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, analizujTekst, -10,
                SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, dodajTekst, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dodajTekst, 40,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, dodajTekst, -10,
                SpringLayout.EAST, this);

        //Layout End

        analizujTekst.addActionListener(new Operacje(1));
        dodajTekst.addActionListener(new Operacje(2));
        add(analizujTekst);
        add(dodajTekst);
    }

    private class Operacje implements ActionListener {

        private int nrAkcji;

        public Operacje(int nrAkcji) {
            this.nrAkcji = nrAkcji;
        }

        public void actionPerformed(ActionEvent arg0) {
            switch (nrAkcji) {
                case 1: {
                    //LSA

                    Repository repository;
                    try {
                        repository = new Repository("repo");
                        SearchResultsCorpus corpus = new SearchResultsCorpus("type:document");
                        corpus.getParameters().setTermSelectionCriterion(CorpusParameters.TermSelection.DF);
                        corpus.getParameters().setTermSelectionThreshold(0);
                        corpus.getParameters().setDimensionalityReduction(CorpusParameters.DimensionalityReduction.NUM);
                        corpus.getParameters().setDimensionalityReductionThreshold(50);
                        corpus.getParameters().setTermWeightGlobal(TermWeighting.GlobalWeight.Entropy);
                        corpus.getParameters().setTermWeightLocal(TermWeighting.LocalWeight.LOGTF);
                        corpus.load(repository);
                        System.out.println("Corpus loaded and Semantic space calculated");
                        System.out.println("Total documents:" + corpus.getPassages().length);

                        PassagesSimilarity distances = new PassagesSimilarity();
                        distances.setCorpus(corpus);
                        distances.start();
                        System.out.println("------------------------LSA------------------------");
                        distances.printResults();

                        RapidAutomaticKeywordExtraction rake = new RapidAutomaticKeywordExtraction();
                        rake.setCorpus(corpus);
                        rake.start();

                        System.out.println("------------------------RAKE------------------------");
                        String[][] test = rake.getResultsStringTable();
                        for(int i=0; i < test.length; i++) {
                            System.out.println("Słowo/a: "+test[i][0]+"; Frekwencja: "+test[i][1]);
                        }

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }


                    //RAKE



                };
                break;
                case 2:{
                    Repository repository1;
                    try {
                        repository1 = new Repository("repo");
                        repository1.addDocumentsInFolder("repoSource");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("Documents added to repository successfully!");
                };
                break;

            }
        }
    }
}
