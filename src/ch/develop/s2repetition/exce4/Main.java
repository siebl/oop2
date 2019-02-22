package ch.develop.s2repetition.exce4;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		assert args != null;
		for (String name : args) {
			try {
				ArrayList<Double> list = ReadFile.readList(name);
				for (Double d : list) {
					System.out.printf(" %.2f%n", d);
				}
				System.out.println("\nDone !!");
			}
			catch( ReadException e ) {
				System.err.println( e.getMessage() );
			}
		}
	}
}
