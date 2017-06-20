package com.pjn.pl;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 * Created by miky on 21/06/2017.
 */
public class Chart extends ApplicationFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Object[]> dane;
    public Chart( String applicationTitle , String chartTitle, ArrayList<Object[]> dane )
    {
        super( applicationTitle );
        this.dane = dane;
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "S�owa",
                "Liczebno��",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
    private CategoryDataset createDataset( )
    {
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        for(int i=0; i < dane.size(); i++) {
            dataset.addValue((Float) Float.valueOf(dane.get(i)[1].toString()),(String)dane.get(i)[0].toString(),"wartosc");
        }

        return dataset;
    }
    public void rysujWykres()
    {
        Chart chart = new Chart("Wykres slupkowy", "Wykres slupkowy",dane);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
