package ch.develop.w1repetition.exce2;

import java.io.IOException;
import java.nio.file.InvalidPathException;
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
			} catch (NumberFormatException | InvalidPathException | IOException e) {
				System.out.println(e);
			}
			System.out.println("end of file: " + name + "\n\n" );
		}
	}
}
