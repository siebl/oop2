package ch.develop.s2repetition.exce2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	public static ArrayList<Double> readList(String fileName)
			throws InvalidPathException, IOException {
		ArrayList<Double> list = new ArrayList<>();
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
		return list;
	}
}
