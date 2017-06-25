package com.pjn.pl;

import javax.swing.*;
import java.awt.*;

public class Strimmer {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(() -> {
			JFrame frame = new Window();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(true);
		});
	}
}
