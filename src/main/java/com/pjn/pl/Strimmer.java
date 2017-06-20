package com.pjn.pl;

import java.io.IOException;
import java.util.Locale;

import morfologik.stemming.WordData;

public class Strimmer {
	public static void main(String[] args) throws Exception {
		new WindowInterface(0);

	}

//	public static void main(String[] args) throws IOException {
//
//		PolishStemmer stemmer = new PolishStemmer();
//		String in = "Nie zabrak?o oczywi?cie wpadek. Najwi?kszym zaskoczeniem okaza? si? dla nas str?j Katarzyny Zieli?skiej, kt?rej ewidentnie o co? chodzi?o, ale wci?? nie wiemy o co";
//		for (String t : in.toLowerCase(new Locale("pl")).split("[\\s\\.\\,]+")) {
//			System.out.println("> '" + t + "'");
//
//			for (WordData wd : stemmer.lookup(t)) {
//				System.out.print(" - " + (wd.getStem() == null ? "<null>" : wd.getStem()));
//			}
//			System.out.println();
//		}
//	}

}
