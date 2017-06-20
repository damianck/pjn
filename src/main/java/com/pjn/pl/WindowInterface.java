package com.pjn.pl;
import javax.swing.JTabbedPane;

/**
 * Created by miky on 20/06/2017.
 */
public class WindowInterface {

    private  Window window;
    public WindowInterface(int id) {
        switch(id) {
            case 0: {
                window = new Window();
                window.add(new MainContainer());
                window.setVisible(true);
            };
            break;
//            case 1: {
//                window = new Window();
//                JTabbedPane jtp = new JTabbedPane();
//                jtp.addTab("TF/TF-IDF", new TFContainer());
//                jtp.addTab("LSA/RAKE", new LSAContainer());
//                window.add(jtp);
//                window.setVisible(true);
//            };
//            break;
        }
    }
}

