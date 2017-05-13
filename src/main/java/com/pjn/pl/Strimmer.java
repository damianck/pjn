package com.pjn.pl;

import java.io.IOException;
import java.util.Locale;

import morfologik.stemming.WordData;

public class Strimmer {

	public static void main(String[] args) throws IOException {

		PolishStemmer stemmer = new PolishStemmer();

		String in = "Nie zabrak³o oczywiœcie wpadek. Najwiêkszym zaskoczeniem okaza³ siê dla nas strój Katarzyny Zieliñskiej, której ewidentnie o coœ chodzi³o, ale wci¹¿ nie wiemy o co.";
		for (String t : in.toLowerCase(new Locale("pl")).split("[\\s\\.\\,]+")) {
			System.out.println("> '" + t + "'");

			for (WordData wd : stemmer.lookup(t)) {
				System.out.print(" - " + (wd.getStem() == null ? "<null>" : wd.getStem()) + ", " + wd.getTag());
			}
			System.out.println();
		}
	}

}
