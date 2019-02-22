package ch.develop.s2repetition.exce3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	public static ArrayList<Double> readList(String fileName) {
		ArrayList<Double> list = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName));
			for (String line : lines) {
				try {
					double d = Double.parseDouble(line);
					list.add(d);
				}
				catch( NumberFormatException e ) {
					System.err.println( "'" + line + "' is not a double!" );
				}
			}
		}
		catch ( IOException e ) {
			System.err.println( "An Error occoured while reading from File: '" + fileName + "' - Skipping execution" );
		}
		return list;
	}
}
